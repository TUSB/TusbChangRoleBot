package jp.skyblock.Core.Observer.Message;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;

public class Reaction {
	public void add(GuildMessageReactionAddEvent event) {
		if (!event.getUser().equals(event.getJDA().getSelfUser())) {
			event.getChannel().getIdLong();
			System.out.println(event.getChannel().getIdLong());
			System.out.println(event.getMessageIdLong());
			event.getUser().openPrivateChannel().queue(ch -> ch.sendMessage(String.format("GuildMessageReactionAddEvent: %s",event.getMessageIdLong())).queue());
		}
	}

	public void remove(GuildMessageReactionRemoveEvent event) {
		if (!event.getUser().equals(event.getJDA().getSelfUser())) {
			event.getChannel().getIdLong();
			System.out.println(event.getChannel().getIdLong());
			System.out.println(event.getMessageIdLong());
			event.getUser().openPrivateChannel().queue(ch -> ch.sendMessage(String.format("GuildMessageReactionRemoveEvent: %s",event.getMessageIdLong())).queue());
		}
	}
}