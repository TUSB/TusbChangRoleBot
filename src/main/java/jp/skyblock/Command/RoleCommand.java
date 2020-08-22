package jp.skyblock.Command;

import jp.skyblock.Core.Const.Constant;
import jp.skyblock.Core.Observer.Message.Received;
import jp.skyblock.Executer.CommandExecIf;
import jp.skyblock.Utility.EmojiUtil;
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

import static jp.skyblock.Core.Const.Enums.EmojiType.TUSB;
import static jp.skyblock.Core.Const.Enums.EmojiType.TUSBCHANG_DOUBT;
import static jp.skyblock.Core.Const.Enums.EmojiType.TUSBCHANG_SAD;
import static jp.skyblock.Core.Const.Enums.EmojiType.TUSBCHANG_TALK;

public class RoleCommand implements CommandExecIf {
	final EmojiUtil emj = Constant.emj;
	final RoleUtil roleUtil = new RoleUtil();

	@Override
	public void execute() throws Exception {
		MessageReceivedEvent event = Received.getEvent();
		String[] cmdParam = Received.getCmdParam();
		Guild guild = Received.getGuild();
		User author = Received.getAuthor();
		Member member = Received.getMember();
		Message message = Received.getMessage();
		MessageChannel channel = Received.getChannel();

		WelcomeMessage wel = new WelcomeMessage(event);
		EmbedBuilder eb = wel.getWelcomeMessage();


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
		// ここまで初期化
		try {
			channel.retrieveMessageById(mesId).queue((m) -> {
				channel.sendTyping().queue();
				m.editMessage(eb.build()).queue();
				pushEmojiList.forEach(emj -> m.addReaction(emj).queue());
			}, (e) -> {
				if (e instanceof ErrorResponseException) {
					ErrorResponseException ex = (ErrorResponseException) e;
					if (ex.getErrorResponse() == ErrorResponse.UNKNOWN_MESSAGE) {
						channel.sendMessage("That message doesn't exist !").queue();
					}
				}
			});

			// Save Properties
//			if (finalIsForce || !new Config().getProperty("OBSERVER_REACTION_CHANNEL_ID").equals("")) {
//				conf.saveList(propPath + propName, "OBSERVER_REACTION_CHANNEL_ID", String.valueOf(channel.getIdLong()));
//			}
//
//			if (finalIsForce || new Config().getProperty("OBSERVER_REACTION_MESSAGE_ID").equals("")) {
//				conf.saveList(propPath + propName, "OBSERVER_REACTION_MESSAGE_ID", String.valueOf(mesId));
//			}

//		throw new Exception("ERROR");
		} catch (Exception e) {
			e.printStackTrace();
			throw sendError(e, "ERROR", event);
		}
	}

	@Override
	public Object executeResponse(Object obj) {
		return null;
	}
}
