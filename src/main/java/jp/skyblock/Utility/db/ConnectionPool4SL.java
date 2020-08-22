/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 ConnectionPool4G.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Utility.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

import static jp.skyblock.Utility.db.ConnectionPool.DBParameter.TransactionType;

/**
 * SQLite Database Connection Pool
 */
public class ConnectionPool4SL extends ConnectionPool {


	protected ConnectionPool4SL() {
		super();
	}

	/**
	 * コネクションプール作成
	 *
	 * @param prop     Database用プロパティ
	 * @param recreate 再生性フラグ、Trueの場合はDataSourceを強制的に再生性する。
	 */
	protected void initializeInstance(final Properties prop, final boolean recreate) {
		synchronized (LOCK) {
			if (dataSource == null || recreate) {
				if (dataSource != null) {
					dataSource.close();
				}

				final HikariConfig config = new HikariConfig();
				config.setDataSourceClassName("org.sqlite.SQLiteDataSource");
				config.setConnectionTimeout(5000);
				config.setAutoCommit(false);
				config.setTransactionIsolation(TransactionType.SERIALIZABLE.getString());
				config.setMinimumIdle(1);
				config.setMaximumPoolSize(1);

				try {
					Class.forName("org.sqlite.JDBC");
					Class.forName("org.sqlite.SQLiteDataSource");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}

				dataSource = new HikariDataSource(config);
			}
		}
	}
}
