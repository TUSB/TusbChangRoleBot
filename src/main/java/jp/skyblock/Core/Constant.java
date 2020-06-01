package jp.skyblock.Core;

import jp.skyblock.TusbChangRoleBot;
import jp.skyblock.Utility.EmojiUtil;
import jp.skyblock.Utility.ExceptionIf;
import org.apache.log4j.Logger;

public class Constant {
	public static final EmojiUtil emj = new EmojiUtil();
	public static final ExceptionIf exceptIf = new ExceptionIf();


	public static final Logger logger = Logger.getLogger(TusbChangRoleBot.class);
	public static final String DISCORD_TOKEN = Config.getProperty("Token");
	public static final String PREFIX = "!";

	//	Command
	public static final String COMMAND_HELLO = "hello";
	public static final String COMMAND_ROLE = "role";

	//	Emoji
	public static final String EmojiSeparator = ":";

	//TODO enum にすべき


}
