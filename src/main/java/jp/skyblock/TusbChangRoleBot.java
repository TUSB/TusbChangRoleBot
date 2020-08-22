/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 TusbChangRoleBot.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock;

import jp.skyblock.Core.BotListener;
import jp.skyblock.Core.Config;
import jp.skyblock.Core.Const.Enums.DatabaseError;
import jp.skyblock.Utility.Exception.ServiceException;
import jp.skyblock.Utility.db.ConnectionPool;
import net.dv8tion.jda.api.JDA;
import org.apache.log4j.PropertyConfigurator;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static jp.skyblock.Core.Config.getDir;
import static jp.skyblock.Core.Const.Constant.DATABASE_PROP_FILE;
import static jp.skyblock.Core.Const.Constant.DISCORD_PROP_FILE;
import static jp.skyblock.Core.Const.Constant.logger;
import static net.dv8tion.jda.api.JDABuilder.createLight;

public class TusbChangRoleBot {
	private static final String CONFIG_DIR = getDir() + File.separator + "config";

	private static final String DISCORD_CONFIG_FILE = File.separator + "Discord.properties";
	private static final String DATABASE_CONFIG_FILE = File.separator + "Database.properties";

	/**
	 * main メソッド
	 * @param args
	 */
	public static void main(String[] args) {
		loadConf();
		initialize();
		checkDatabase();
	}

	/**
	 * Config File Load
	 */
	private static void loadConf() {
		try {
			PropertyConfigurator.configure("config/log4j.properties");
			DISCORD_PROP_FILE = CONFIG_DIR + DISCORD_CONFIG_FILE;
			DATABASE_PROP_FILE = CONFIG_DIR + DATABASE_CONFIG_FILE;
			logger.info("Config Load");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 各種インスタンスをイニシャライズ
	 */
	private static void initialize() {
		databaseInitialize();
		discordInitialize();
	}

	/**
	 * Discord用インスタンスを初期化
	 */
	private static void discordInitialize(){
		try {
			String DISCORD_TOKEN = new Config().getValue(DISCORD_PROP_FILE, "Token");
			JDA jda = createLight(DISCORD_TOKEN)
					.addEventListeners(new BotListener())
					.build();
			jda.awaitReady();
			System.out.println("Finished Building JDA!");
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}


	/**
	 * Databaseインスタンスを作成する。
	 */
	private static void databaseInitialize(){
		try {
			ConnectionPool.initialize(DATABASE_PROP_FILE);
		} catch (Exception e) {
			throw new ServiceException(DatabaseError.DatabaseInit, e);
		}
	}

	/**
	 * Database の接続確認をする。
	 */
	private static void checkDatabase(){
		try (Connection con = ConnectionPool.getConnection()) {
			Statement statement = con.createStatement();
			String sql = "SELECT CURRENT_TIMESTAMP;";
			statement.executeQuery(sql);
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				String now = rs.getString("CURRENT_TIMESTAMP");
				System.out.println("現在時間: " + now);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}