package jp.skyblock;

import jp.skyblock.Core.Config;
import jp.skyblock.Core.Constant;
import jp.skyblock.Executer.BotExecute;

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
			//TODO config.load(String: LoadするConfigFilePATH);
			config.load();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}