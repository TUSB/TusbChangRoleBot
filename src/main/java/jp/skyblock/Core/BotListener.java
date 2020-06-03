/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 BotListener.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Core;

import jp.skyblock.Core.Observer.Message.Reaction;
import jp.skyblock.Core.Observer.Message.Received;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class BotListener extends ListenerAdapter {
	private final Received received = new Received();
	private final Reaction reaction = new Reaction();

	/**
	 * メッセージ受信Event
	 *
	 * @param event
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot()) return;
		received.sendMessage(event);
	}

	/**
	 * リアクション追加Event
	 *
	 * @param event
	 */
	@Override
	public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
		reaction.add(event);
	}

	/**
	 * リアクション削除Event
	 *
	 * @param event
	 */
	@Override
	public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
		reaction.remove(event);
	}
}
