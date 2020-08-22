/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 ExceptionIf.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Utility.Exception;

import jp.skyblock.Core.Const.Constant;
import jp.skyblock.Core.Observer.Message.Received;
import jp.skyblock.Utility.EmojiUtil;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandExceptionIf {


	Received received = new Received();

	static Exception commandException(Exception e, String ErrorMessage, MessageReceivedEvent event) {
		EmojiUtil emj = Constant.emj;
		Guild guild = Received.getGuild();
		User author = Received.getAuthor();
		MessageChannel channel = Received.getChannel();
		String msg = Received.getMessage().getContentDisplay();

		String errorMes = String.format("(%s)[%s %s]<%s>: %s \n %s",
				event.getResponseNumber(), guild.getName(), channel.getName(), author, msg, e.getLocalizedMessage());
		channel
				.sendMessage(emj.ReplaceEmojiMessage(errorMes))
				.complete();
		return e;
	}

	/**
	 * @param e
	 * @return
	 */

	default Exception commandException(Exception e) {
		return e;
	}

	default Exception commandException(Exception e, String ErrorMessage) {
		return e;
	}
}
