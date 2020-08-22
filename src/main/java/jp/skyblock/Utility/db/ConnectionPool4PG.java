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
 * Postgres Database Connection Pool
 */
public class ConnectionPool4PG extends ConnectionPool {

	protected ConnectionPool4PG() {
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

				final DBParameter params = new DBParameter();
				params.setDatabase(prop.getProperty("db"));
				params.setServers(prop.getProperty("hosts").split(","));
				params.setPort(prop.getProperty("port"));
				params.setUsername(prop.getProperty("user"));
				params.setPassword(prop.getProperty("password"));
				params.setMinIdel(Integer.parseInt(prop.getProperty("min")));
				params.setMaxPool(Integer.parseInt(prop.getProperty("max")));

				final HikariConfig config = new HikariConfig();
				config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
				config.addDataSourceProperty("user", params.getUsername());
				config.addDataSourceProperty("password", params.getPassword());
				config.addDataSourceProperty("serverName", params.getServers()[serverIndex]);
				config.addDataSourceProperty("portNumber", params.getPort());
				config.addDataSourceProperty("databaseName", params.getDatabase());

				config.setConnectionTimeout(5000);
				config.setAutoCommit(false);
				config.setTransactionIsolation(TransactionType.READ_COMMITTED.getString());
				config.setMinimumIdle(params.getMinIdel());
				config.setMaximumPoolSize(params.getMaxPool());

				try {
					Class.forName("org.postgresql.Driver");
					Class.forName("org.postgresql.ds.PGSimpleDataSource");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}

				dbParameter = params;
				dataSource = new HikariDataSource(config);
			}
		}
	}
}
