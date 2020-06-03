package jp.skyblock.Core.Observer.Message;

import jp.skyblock.Command.CommandExecIf;
import jp.skyblock.Core.BotListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

import static jp.skyblock.Command.CommandExecIf.getCommandExec;
import static jp.skyblock.Core.Const.Constant.PREFIX;
import static jp.skyblock.Core.Const.Constant.logger;

public class Received extends BotListener {

	private String getCommand(String msg) {
		return PREFIX + msg;
	}

	private Boolean isCommand(String msg) {
		return msg.length() > 1 && PREFIX.equals(StringUtils.left(msg, 1));
	}

	/**
	 * @param event
	 */
	public void sendMessage(MessageReceivedEvent event) {
		try {
			//これらは JDA のすべてのイベントで提供されます。
			JDA jda = event.getJDA();
			long responseNumber = event.getResponseNumber();
			Guild guild = event.getGuild();
			User author = event.getAuthor();
			Message message = event.getMessage();
			MessageChannel channel = event.getChannel();
			TextChannel textChannel = event.getTextChannel();
			Member member = event.getMember();

			String msg = message.getContentDisplay();
			String name = message.isWebhookMessage() ? author.getName() : Objects.requireNonNull(member).getEffectiveName();

			if (event.isFromType(ChannelType.TEXT)) {
				logger.info(String.format("(%s)[%s %s]<%s>: %s", responseNumber, guild.getName(), textChannel.getName(), name, msg));

				if (isCommand(msg)) {
					CommandExecIf command = getCommandExec(msg);
					try {
						CommandExecIf.CommandEvent.setEvent(event);
						command.execute();

					} catch (Exception e) {
						EmbedBuilder eb = new EmbedBuilder();
						TextChannel c = guild.getTextChannelById(714836325592989737L);
						eb.setColor(Color.RED);
						eb.setTitle(e.getMessage());
						eb.setAuthor(author.getName());
						eb.setDescription(Arrays.toString(e.getStackTrace()));
						eb.setFooter("(c) TUSB ~ 想像を超えた創造を ~", guild.getIconUrl());
						Objects.requireNonNull(c).sendMessage(eb.build())
								.queue();
						eb.clear();

					}
				}
			} else if (event.isFromType(ChannelType.PRIVATE)) {
				PrivateChannel privateChannel = event.getPrivateChannel();
				logger.info(String.format("[PRIV]<%s>: %s\n", author.getName(), msg));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
