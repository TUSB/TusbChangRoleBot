package jp.skyblock.Executer;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandExecuter {
	MessageReceivedEvent event;
	User author ;
	Message message;
	MessageChannel channel ;
	public CommandExecuter(){

	}
	public CommandExecuter(User author, Message message, MessageChannel channel){
		this.author = author;
		this.message = message;
		this.channel = channel;

	}

	public Object execute(String command){

		return null;
	}
}
