package jp.skyblock.Core.Observer.Message;

import jp.skyblock.Executer.CommandExecIf;
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

import java.util.Objects;

import static jp.skyblock.Core.Const.Constant.PREFIX;
import static jp.skyblock.Core.Const.Constant.logger;
import static jp.skyblock.Executer.CommandExecIf.getInstance;

public class Received {

	/**
	 * @return event
	 */

	private static String[] cmdParam;
	private static MessageReceivedEvent event;
	private static Guild guild;
	private static User author;
	private static Member member;
	private static Message message;
	private static MessageChannel channel;
	private static TextChannel textChannel;
	private static String msg;

	public static Guild getGuild() {
		return guild;
	}

	public static User getAuthor() {
		return author;
	}

	public static Member getMember() {
		return member;
	}

	public static Message getMessage() {
		return message;
	}

	public static MessageChannel getChannel() {
		return channel;
	}

	public static MessageReceivedEvent getEvent() {
		return event;
	}

	public void setEvent(MessageReceivedEvent event) {
		Received.event = event;
	}

	public static String[] getCmdParam() {
		return cmdParam;
	}

	public void setCmdParam(String[] cmdParam) {
		Received.cmdParam = cmdParam;
	}

	private String getCommand(String msg) {
		return PREFIX + msg;
	}

	private Boolean isCommand(String msg) {
		return msg.length() > 1 && PREFIX.equals(StringUtils.left(msg, 1));
	}

	public MessageChannel getTextChannel() {
		return textChannel;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * @param event
	 */
	public void sendMessage(MessageReceivedEvent event) {
		try {
			//これらは JDA のすべてのイベントで提供されます。
			this.setEvent(event);
			JDA jda = event.getJDA();
			long responseNumber = event.getResponseNumber();
			guild = event.getGuild();
			author = event.getAuthor();
			member = guild.getMember(author);
			message = event.getMessage();
			channel = event.getChannel();
			textChannel = event.getTextChannel();
			member = event.getMember();

			String msg = message.getContentDisplay();
			String name = message.isWebhookMessage() ? author.getName() : Objects.requireNonNull(member).getEffectiveName();

			if (event.isFromType(ChannelType.TEXT)) {
				logger.info(String.format("(%s)[%s %s]<%s>: %s", responseNumber, guild.getName(), textChannel.getName(), name, msg));

				if (isCommand(msg)) {
					setCmdParam(msg.split(" "));
					CommandExecIf command = getInstance(getCmdParam());
					try {
						command.execute();
					} catch (Exception e) {
						e.printStackTrace();
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
