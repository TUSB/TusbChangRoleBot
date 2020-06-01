package jp.skyblock;

import jp.skyblock.Core.Config;
import jp.skyblock.Core.Constant;
import jp.skyblock.Executer.BotExecute;
import org.apache.log4j.PropertyConfigurator;

public class TusbChangRoleBot {

	public static void main(String[] args) {
		loadConf();
		new Constant();
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