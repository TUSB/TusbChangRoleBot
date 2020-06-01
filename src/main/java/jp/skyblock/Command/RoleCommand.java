package jp.skyblock.Command;

import jp.skyblock.Core.Config;
import jp.skyblock.Core.Constant;
import jp.skyblock.Utility.EmojiUtil;
import jp.skyblock.Utility.ExceptionIf;
import jp.skyblock.Utility.RoleUtil;
import jp.skyblock.Utility.WelcomeMessage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.ErrorResponse;

import java.util.ArrayList;

import static jp.skyblock.Core.Config.CONFIG_DIR;
import static jp.skyblock.Core.Config.DISCORD_CONFIG_FILE;
import static jp.skyblock.Core.Config.getProperty;
import static jp.skyblock.Core.EmojiType.TUSB;
import static jp.skyblock.Core.EmojiType.TUSBCHANG_DOUBT;
import static jp.skyblock.Core.EmojiType.TUSBCHANG_SAD;
import static jp.skyblock.Core.EmojiType.TUSBCHANG_TALK;

public class RoleCommand implements CommandExecIf {
	final EmojiUtil emj = Constant.emj;
	final ExceptionIf exceptIf = Constant.exceptIf;
	final RoleUtil roleUtil = new RoleUtil();

	@Override
	public void execute() throws ExceptionIf {
		MessageReceivedEvent event = CommandExecIf.CommandEvent.getEvent();
		String[] cmdParam = CommandExecIf.CommandEvent.cmdParam;
		Guild guild = event.getGuild();
		User author = event.getAuthor();
		Member member = guild.getMember(author);
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();

		WelcomeMessage wel = new WelcomeMessage(event);
		EmbedBuilder eb = wel.getWelcomeMessage();

		Config conf = new Config();
		String propPath = CONFIG_DIR;
		String propName = DISCORD_CONFIG_FILE;

		// コマンド履行をAdminのみとする。
		if (!roleUtil.isAdmin(member)) return;

		//付与するリアクションList
		ArrayList<String> pushEmojiList = new ArrayList<String>() {
			{
				add(emj.getReaction(TUSB.getName()));
				add(emj.getReaction(TUSBCHANG_TALK.getName()));
				add(emj.getReaction(TUSBCHANG_SAD.getName()));
				add(emj.getReaction(TUSBCHANG_DOUBT.getName()));
			}
		};

		long mesId = 0L;
		boolean isForce; // デフォルトTrue
		try {
			try {
				mesId = Long.parseLong(cmdParam[1]);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			isForce = Boolean.parseBoolean(cmdParam[2]);
		} catch (Exception e) {
			isForce = true;
		}
		boolean finalIsForce = isForce;

		// ここまで初期化
		try {
			channel.retrieveMessageById(mesId).queue((m) -> {
				channel.sendTyping().queue();
				m.editMessage(" ").queue();
				m.editMessage(eb.build()).queue();
				pushEmojiList.forEach(emj -> m.addReaction(emj).queue());
			}, (e) -> {
				if (e instanceof ErrorResponseException) {
					ErrorResponseException ex = (ErrorResponseException) e;
					if (ex.getErrorResponse() == ErrorResponse.UNKNOWN_MESSAGE) {
						channel.sendMessage("That message doesn't exist !").queue();
					}
				}
				throw new RuntimeException(e);
			});

			// Save Properties
			if (finalIsForce || !getProperty("OBSERVER_REACTION_CHANNEL_ID").equals("")) {
				conf.saveList(propPath, propName, "OBSERVER_REACTION_CHANNEL_ID", String.valueOf(channel.getIdLong()));
			}

			if (finalIsForce || getProperty("OBSERVER_REACTION_MESSAGE_ID").equals("")) {
				conf.saveList(propPath, propName, "OBSERVER_REACTION_MESSAGE_ID", String.valueOf(mesId));
			}


		} catch (Exception e) {
			Exception ex = exceptIf.commandException(e, event);
			throw new ExceptionIf(ex);
		}
	}
}
