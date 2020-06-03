/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 ExceptionIf.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Utility;

import jp.skyblock.Core.Const.Constant;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ExceptionIf extends Exception {
	final EmojiUtil emj = Constant.emj;

	public ExceptionIf() {
	}

	public ExceptionIf(Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}

	public Exception commandException(Exception e, MessageReceivedEvent event) {
		Guild guild = event.getGuild();
		User author = event.getAuthor();
		String msg = event.getMessage().getContentDisplay();
		MessageChannel channel = event.getChannel();

		String errorMes = String.format("(%s)[%s %s]<%s>: %s \n %s",
				event.getResponseNumber(), guild.getName(), channel.getName(), author, msg, e.getLocalizedMessage());
		channel
				.sendMessage(emj.ReplaceEmojiMessage(errorMes))
				.complete();
		return e;
	}
}
