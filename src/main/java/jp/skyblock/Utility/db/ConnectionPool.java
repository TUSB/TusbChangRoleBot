/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 ConnectionPool.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Utility.db;

import com.zaxxer.hikari.HikariDataSource;
import jp.skyblock.Core.Const.Enums.DatabaseError;
import jp.skyblock.Utility.Exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import static jp.skyblock.Core.Config.loadProperties;

public abstract class ConnectionPool {

	/**
	 * dataSourceを確認するタイマー間隔（秒）
	 */
	private final static int DATASOURCE_CHECK_INTERVAL_SEC = 60;
	/**
	 * Serverのインデックス
	 */
	protected static int serverIndex = 0;
	/**
	 * シングルトンインスタンス
	 */
	private static ConnectionPool instance;
	/**
	 * dataSourceを一定間隔で確認するタイマー
	 */
	private static Timer dataSourceCheckTimer = null;
	protected final Object LOCK = new Object();
	/**
	 * dbProperties
	 */
	protected Properties dbProperties;
	/**
	 * dbParameter
	 */
	protected DBParameter dbParameter;
	/**
	 * dataSource
	 */
	protected HikariDataSource dataSource;


	protected ConnectionPool() {
		super();
	}

	/**
	 * インスタンス取得する。
	 * インスタンスが生成されていない場合はnullを返す。
	 *
	 * @return
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}

	/**
	 * DB接続取得
	 *
	 * @return Connection DataSource が生成されていない場合はnull
	 * @throws SQLException DB接続が取得できなかった場合
	 */
	public static Connection getConnection() throws SQLException {
		return getConnection(false);
	}

	/**
	 * DB接続取得
	 *
	 * @param retry 接続先を変更し、再接続
	 * @return Connection DataSource が生成されていない場合はnull
	 * @throws SQLException DB接続が取得できなかった場合
	 */
	public static Connection getConnection(final Boolean retry) throws SQLException {
		Connection con = null;
		try {
			if (getInstance().dataSource != null) {
				con = getInstance().dataSource.getConnection();
			}
			throw new ServiceException(DatabaseError.DatabaseState,
					"Server : " + getDBParameter().getServers()[serverIndex]);
		} catch (Exception e) {
			if (retry) con = getReConnection();
			else throw e;
		} finally {
			if (con == null) throw new ServiceException(DatabaseError.DatabaseState, "con is null.");
		}
		return con;
	}

	/**
	 * 接続先を変更し、再接続を行う
	 *
	 * @return Connection
	 * @throws SQLException DB接続が取得できなかった場合
	 */
	private static Connection getReConnection() throws SQLException {
		synchronized (getInstance().LOCK) {
			final int length = getInstance().dbParameter.servers.length;
			if ((++serverIndex) >= length) serverIndex = 0;
			getInstance().initializeInstance(getInstance().dbProperties, true);
			return ConnectionPool.getConnection(false);
		}
	}

	/**
	 * DB接続プールの接続パラメータ取得
	 *
	 * @return 接続パラメータ
	 */
	public static DBParameter getDBParameter() {
		synchronized (getInstance().LOCK) {
			if (getInstance().dbParameter == null) {
				getInstance().dbParameter = new DBParameter();
			}
		}
		return getInstance().dbParameter;
	}

	/**
	 * createInstance
	 *
	 * @return ConnectionPool インスタンス
	 */
	private static ConnectionPool createInstance(final Properties prop) {
		try {
			if (instance == null) {
				Class<?> c = Class.forName(prop.getProperty("connection_pool_class"));
				instance = (ConnectionPool) c.newInstance();
				getInstance().dbProperties = prop;
				getInstance().startCheckDataSourceTimer();
			}
			return instance;
		} catch (Exception e) {
			throw new ServiceException(DatabaseError.DatabaseInit, e);
		}
	}

	/**
	 * initialize
	 *
	 * @param prop Database Properties File
	 */
	public static void initialize(final String prop) {
		Properties p = loadProperties(prop);
		if (instance == null) {
			instance = createInstance(p);
		}
		getInstance().initializeInstance(p);
	}

	/**
	 * initialize
	 *
	 * @param prop     Database Properties File
	 * @param recreate 再生性フラグ、Trueの場合はDataSourceを強制的に再生性する。
	 */
	public static void initialize(final Properties prop, final boolean recreate) {
		if (instance == null) {
			instance = createInstance(prop);
		}
		getInstance().initializeInstance(prop, recreate);
	}

	/**
	 * コネクションプール終了処理
	 */
	public static void close() {
		synchronized (getInstance().LOCK) {
			if (getInstance().dataSource != null) {
				getInstance().dataSource.close();
				getInstance().dataSource = null;
			}
		}
		if (dataSourceCheckTimer != null) {
			try {
				dataSourceCheckTimer.cancel();
			} catch (Exception ignored) {
			}
		}
	}

	/**
	 * TimerTask を実行
	 *
	 * @param r TimerTask 実行するRunner
	 * @return TimerTask
	 */
	private TimerTask executeTimer(Runnable r) {
		return new TimerTask() {
			@Override
			public void run() {
				r.run();
			}
		};
	}

	/**
	 * initializeInstance
	 */
	private void initializeInstance(final Properties prop) {
		if (instance == null) instance = createInstance(prop);
		getInstance().initializeInstance(prop, false);
	}

	/**
	 * コネクションプール作成
	 *
	 * @param prop     Database用プロパティ
	 * @param recreate 再生性フラグ、Trueの場合はDataSourceを強制的に再生性する。
	 */
	abstract protected void initializeInstance(final Properties prop, final boolean recreate);

	/**
	 * startCheckDataSourceTimer.
	 */
	private void startCheckDataSourceTimer() {
		/**
		 * DataSource接続確認
		 */
		final Runnable dataCheckRunnable = () -> {
			try {
				if (getInstance().dataSource == null) {
					getInstance().initializeInstance(getInstance().dbProperties);
				}
			} catch (Exception e) {
				throw new ServiceException(DatabaseError.DatabaseState, e);
			}
		};

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND));

		dataSourceCheckTimer = new Timer("DatabaseSchedule");
		dataSourceCheckTimer.scheduleAtFixedRate(executeTimer(dataCheckRunnable),
				calendar.getTime(), DATASOURCE_CHECK_INTERVAL_SEC * 1000);
	}


	/**
	 * Database 接続パラメータClass
	 */
	protected static class DBParameter {
		private String[] servers;
		private String port;
		private String database;
		private String username;
		private String password;
		private int minIdel = -1;
		private int maxPool = -1;

		/**
		 * @return the server
		 */
		public String[] getServers() {
			return servers;
		}

		/**
		 * server the server to set
		 */
		public void setServers(final String[] server) {
			this.servers = server;
		}

		/**
		 * @return the port
		 */
		public String getPort() {
			return port;
		}

		/**
		 * port the port to set
		 */
		public void setPort(final String port) {
			this.port = port;
		}

		/**
		 * @return the database
		 */
		public String getDatabase() {
			return database;
		}

		/**
		 * database the database to set
		 */
		public void setDatabase(final String database) {
			this.database = database;
		}

		/**
		 * @return username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * username セットする username
		 */
		public void setUsername(final String username) {
			this.username = username;
		}

		/**
		 * @return password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * password セットする password
		 */
		public void setPassword(final String password) {
			this.password = password;
		}

		/**
		 * @return minIdel
		 */
		public int getMinIdel() {
			return minIdel;
		}

		/**
		 * minIdel セットする minIdel
		 */
		public void setMinIdel(final int minIdel) {
			this.minIdel = minIdel;
		}

		/**
		 * @return maxPool
		 */
		public int getMaxPool() {
			return maxPool;
		}

		/**
		 * maxPool セットする maxPool
		 */
		public void setMaxPool(final int maxPool) {
			this.maxPool = maxPool;
		}

		/**
		 * トランザクションがサポートされていないことを示す定数です。
		 * static int	TRANSACTION_NONE
		 * <p>
		 * ダーティ読込みは抑制され、繰返し不可の読み込みおよびファントム読込みが起こることを示す定数です。
		 * static int	TRANSACTION_READ_COMMITTED
		 * <p>
		 * ダーティ読み込み、繰返し不可の読み込み、およびファントム読込みが起こることを示す定数です。
		 * static int	TRANSACTION_READ_UNCOMMITTED
		 * <p>
		 * ダーティ読み込みおよび繰返し不可の読込みは抑制され、ファントム読込みが起こることを示す定数です。
		 * static int	TRANSACTION_REPEATABLE_READ
		 * <p>
		 * ダーティ読み込み、繰返し不可の読み込み、およびファントム読込みが抑制されることを示す定数です。
		 * static int	TRANSACTION_SERIALIZABLE
		 */
		enum TransactionType {
			NONE("TRANSACTION_NONE"),
			READ_COMMITTED("TRANSACTION_READ_COMMITTED"),
			READ_UNCOMMITTED("TRANSACTION_READ_UNCOMMITTED"),
			REPEATABLE_READ("TRANSACTION_REPEATABLE_READ"),
			SERIALIZABLE("TRANSACTION_SERIALIZABLE");
			String type;

			TransactionType(String type) {
				this.type = type;
			}

			public String getString() {
				return type;
			}
		}
	}
}
