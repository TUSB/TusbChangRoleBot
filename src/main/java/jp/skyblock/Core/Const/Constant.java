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

import jp.skyblock.TusbChangRoleBot;
import jp.skyblock.Utility.EmojiUtil;
import org.apache.log4j.Logger;

public class Constant {
	public static final EmojiUtil emj = new EmojiUtil();
	public static final Logger logger = Logger.getLogger(TusbChangRoleBot.class);
	public static final String PREFIX = "!";
	//	Command
	public static final String COMMAND_HELLO = "hello";
	public static final String COMMAND_ROLE = "role";
	//	Emoji
	public static final String EmojiSeparator = ":";
	public static String DATABASE_PROP_FILE;
	public static String DISCORD_PROP_FILE;

}
