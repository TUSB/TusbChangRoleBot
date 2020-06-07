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
import org.apache.log4j.PropertyConfigurator;

public class TusbChangRoleBot {

	public static void main(String[] args) {
		loadConf();
		Constant.logger.info("Config Load");
		new BotExecute();
	}

	private static void loadConf() {
		Config config = new Config();
		try {
			PropertyConfigurator.configure("config/log4j.properties");
			// Discord 用ConfigファイルをLoad
			config.load(Config.CONFIG_DIR + Config.DISCORD_CONFIG_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}