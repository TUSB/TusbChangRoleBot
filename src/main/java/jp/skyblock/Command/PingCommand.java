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

import jp.skyblock.Core.Config;
import jp.skyblock.Core.Const.Constant;
import jp.skyblock.Core.Observer.Message.Received;
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

import static jp.skyblock.Core.Const.Enums.EmojiType.TUSB;


public class PingCommand implements CommandExecIf {
	final EmojiUtil emj = Constant.emj;
	final RoleUtil roleUtil = new RoleUtil();
	final Received received = new Received();

	@Override
	public void execute() {
		MessageReceivedEvent event = received.getEvent();
		String[] cmdParam = received.getCmdParam();
		Guild guild = event.getGuild();
		User author = event.getAuthor();
		Member member = guild.getMember(author);
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();

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

		}
	}

	@Override
	public Object executeResponse(Object obj) {
		return null;
	}


}
