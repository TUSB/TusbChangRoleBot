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

import jp.skyblock.Core.Config;
import jp.skyblock.Core.Const.Constant;

public class ConnectionPool4J {
	Config config = Constant.config;
	public ConnectionPool4J(){

	}
	protected void configLoader(String baseFile){
		config.load(baseFile);
		System.out.println(config.getPropertyList("user"));

	}

}
