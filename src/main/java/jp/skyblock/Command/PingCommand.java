/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 PingCommand.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Command;

import jp.skyblock.Core.Const.Constant;
import jp.skyblock.Utility.EmojiUtil;
import jp.skyblock.Utility.ExceptionIf;
import jp.skyblock.Utility.WelcomeMessage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static jp.skyblock.Core.Const.Enums.EmojiType.TUSB;


public class PingCommand implements CommandExecIf {
	final EmojiUtil emj = Constant.emj;
	final ExceptionIf exceptIf = Constant.exceptIf;


	@Override
	public void execute() {
		MessageReceivedEvent event = CommandExecIf.CommandEvent.getEvent();

		Guild guild = event.getGuild();
		User author = event.getAuthor();
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		String msg = message.getContentDisplay();

		WelcomeMessage wel = new WelcomeMessage(event);
		EmbedBuilder eb = wel.getWelcomeMessage();
		try {
			channel.sendTyping().queue();
			channel.sendMessage(eb.build())
					.complete()
					.addReaction(emj.getReaction(TUSB.getName()))
					.queue();
			eb.clear();

		} catch (Exception e) {
			Exception ex = exceptIf.commandException(e, event);
			throw new RuntimeException(ex);
		}
	}
}
