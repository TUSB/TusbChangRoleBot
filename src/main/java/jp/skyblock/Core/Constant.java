package jp.skyblock.Core;

import jp.skyblock.TusbChangRoleBot;
import jp.skyblock.Utility.Emoji;
import jp.skyblock.Utility.ExceptionIf;
import org.apache.log4j.Logger;

import java.io.File;

public class Constant {
	public static final Emoji emj = new Emoji();
	public static final ExceptionIf exceptIf = new ExceptionIf();


	public static final String CONFIG_DIR = File.separator + "config";
	public static final String DISCORD_CONFIG_FILE = File.separator + "Discord.properties";
	public static final Logger logger = Logger.getLogger(TusbChangRoleBot.class);
	public static final String DISCORD_TOKEN = Config.getProperty("Token");
	public static final String PREFIX = "!";

	//	Command
	public static final String COMMAND_HELLO = "hello";
	public static final String COMMAND_ROLE = "role";

	//	Emoji
	public static final String EmojiSeparator = ":";

	//TODO enum にすべき

//	//	<:tusb:290388758971285514>
//	public static final String EMOJI_NAME_TUSB				= "tusb";
//	public static final Long EMOJI_ID_TUSB				= 290388758971285514L;
//
//	//	<:TUSBCHANG_TALK:321283110270664704>
//	public static final String EMOJI_NAME_TUSBCHANG_TALK 	= "TUSBCHANG_TALK";
//	public static final Long EMOJI_ID_TUSBCHANG_TALK 	= 321283110270664704L;
//
//	//	<:TUSBCHANG_SAD:321280905224257537
//	public static final String EMOJI_NAME_TUSBCHANG_SAD 		= "TUSBCHANG_SAD";
//	public static final Long EMOJI_ID_TUSBCHANG_SAD 		= 321280905224257537L;
//
//	//	<:TUSBCHANG_DOUBT:321281996728958977>
//	public static final String EMOJI_NAME_TUSBCHANG_DOUBT 	= "TUSBCHANG_DOUBT";
//	public static final Long EMOJI_ID_TUSBCHANG_DOUBT 	= ;
//
//	//	<:TUSBCHANG_ANGRY:321269696479363073>
//	public static final String EMOJI_NAME_TUSBCHANG_ANGRY 	= "TUSBCHANG_ANGRY";
//	public static final Long EMOJI_ID_TUSBCHANG_ANGRY 	= 321269696479363073L;
//
//	//	<:TUSBCHANG:321270021126881282>
//	public static final String EMOJI_NAME_TUSBCHANG		 	= "TUSBCHANG";
//	public static final Long EMOJI_ID_TUSBCHANG		 	= 321270021126881282L;
//
//	//	<:s1_security_pole_single:660817748871479345>
//	public static final String EMOJI_NAME_S1_SECURITY_POLE_SINGLE 	= "s1_security_pole_single";
//	public static final Long EMOJI_ID_S1_SECURITY_POLE_SINGLE 	= 660817748871479345L;
//
//	//	<:s2_security_pole_dual:660817775375417356>
//	public static final String EMOJI_NAME_S2_SECURITY_POLE_DUAL 	= "s2_security_pole_dual";
//	public static final Long EMOJI_ID_S2_SECURITY_POLE_DUAL 	= 660817775375417356L;
//
//	//	<:s3_security_pole_triple:660817796032102400>
//	public static final String EMOJI_NAME_S3_SECURITY_POLE_TRIPLE 	= "s3_security_pole_triple";
//	public static final Long EMOJI_ID_S3_SECURITY_POLE_TRIPLE 	= 660817796032102400L;
//
//	//	<:e1_demon_eye_icon:660817340778151936>
//	public static final String EMOJI_NAME_E1_DEMON_EYE_ICON 		= "e1_demon_eye_icon";
//	public static final Long EMOJI_ID_E1_DEMON_EYE_ICON 		= 660817340778151936L;
//
//	//	<:e2_poison_eye_icon:660817390719729674>
//	public static final String EMOJI_NAME_E2_POISON_EYE_ICON 	= "e2_poison_eye_icon";
//	public static final Long EMOJI_ID_E2_POISON_EYE_ICON 	= 660817390719729674L;
//
//	//	<:e3_crimson_eye_icon:660817422940504064>
//	public static final String EMOJI_NAME_E3_CRIMSON_EYE_ICON 	= "e3_crimson_eye_icon";
//	public static final Long EMOJI_ID_E3_CRIMSON_EYE_ICON 	= 660817422940504064L;
//
//	//	<:e4_lunatic_eye_icon:660817453470711819>
//	public static final String EMOJI_NAME_E4_LUNATIC_EYE_ICON 	= "e4_lunatic_eye_icon";
//	public static final Long EMOJI_ID_E4_LUNATIC_EYE_ICON 	= 660817453470711819L;
//
//	//	<:e5_frozen_eye_icon:660817483292344345>
//	public static final String EMOJI_NAME_E5_FROZEN_EYE_ICON 	= "e5_frozen_eye_icon";
//	public static final Long EMOJI_ID_E5_FROZEN_EYE_ICON 	= 660817483292344345L;
//
//	//	<:e6_nightmare_eye_icon:660817506906406922>
//	public static final String EMOJI_NAME_E6_NIGHTMARE_EYE_ICON  = "e6_nightmare_eye_icon";
//	public static final Long EMOJI_ID_E6_NIGHTMARE_EYE_ICON  = 660817506906406922L;
//
//	//	<:e7_aurora_maker_icon:660817527655366666>
//	public static final String EMOJI_NAME_E7_AURORA_MAKER_ICON	= "e7_aurora_maker_icon";
//	public static final Long EMOJI_ID_E7_AURORA_MAKER_ICON	= 660817527655366666L;
//
//	//	<:genocide_bomb_icon:660819692427935764>
//	public static final String EMOJI_NAME_GENOCIDE_BOMB_ICON 	= "genocide_bomb_icon";
//	public static final Long EMOJI_ID_GENOCIDE_BOMB_ICON 	= 660819692427935764L;
//
//	//	<:demon_archer_icon:660817556860305408>
//	public static final String EMOJI_NAME_DEMON_ARCHER_ICON		= "demon_archer_icon";
//	public static final Long EMOJI_ID_DEMON_ARCHER_ICON		= 660817556860305408L;
//
//	//	<:Abyss_man_Jr:319934198804774912>
//	public static final String EMOJI_NAME_ABYSS_MAN_JR			= "Abyss_man_Jr";
//	public static final Long EMOJI_ID_ABYSS_MAN_JR			= 319934198804774912L;



}
