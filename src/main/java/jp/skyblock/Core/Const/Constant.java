/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 Constant.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Core.Const;

import jp.skyblock.Core.Config;
import jp.skyblock.TusbChangRoleBot;
import jp.skyblock.Utility.EmojiUtil;
import jp.skyblock.Utility.ExceptionIf;
import org.apache.log4j.Logger;

import java.io.File;

import static jp.skyblock.Core.Config.getDir;

public class Constant {
	public static final EmojiUtil emj = new EmojiUtil();
	public static final Config config = new Config();


	public static final Logger logger = Logger.getLogger(TusbChangRoleBot.class);
	public static final String DISCORD_TOKEN = new Config().getProperty("Token");
	public static final String PREFIX = "!";

	//	Command
	public static final String COMMAND_HELLO = "hello";
	public static final String COMMAND_ROLE = "role";

	//	Emoji
	public static final String EmojiSeparator = ":";

}
