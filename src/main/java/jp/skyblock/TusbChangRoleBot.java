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

import jp.skyblock.Core.Config;
import jp.skyblock.Core.Const.Constant;
import jp.skyblock.Executer.BotExecute;
import jp.skyblock.Utility.db.ConnectionPool;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

import static jp.skyblock.Core.Config.getDir;
import static jp.skyblock.Core.Const.Constant.*;

public class TusbChangRoleBot {

	private static final String CONFIG_DIR = getDir() + File.separator + "config";
	private static final String DISCORD_CONFIG_FILE = File.separator + "Discord.properties";
	private static final String DATABASE_CONFIG_FILE = File.separator + "Database.properties";

	public static void main(String[] args) {
		loadConf();
		logger.info("Config Load");
		new BotExecute();
	}

	private static void loadConf() {
		Config config = new Config();
		try {
			PropertyConfigurator.configure("config/log4j.properties");
			// Discord 用ConfigファイルをLoad
			config.load(CONFIG_DIR + DISCORD_CONFIG_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}