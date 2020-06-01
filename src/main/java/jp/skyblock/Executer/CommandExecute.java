package jp.skyblock.Executer;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Deprecated
public class CommandExecute {

	MessageReceivedEvent event;

	public CommandExecute() {
	}

	public CommandExecute(MessageReceivedEvent event) {
		this.event = event;
	}

	/**
	 * @return event
	 */
	public MessageReceivedEvent getEvent() {
		return event;
	}

	/**
	 * @param event セットする event
	 */
	public void setEvent(MessageReceivedEvent event) {
		this.event = event;
	}

	public Object execute() throws Exception {
		System.out.println("DEFAULT COMMAND ");
		return null;
	}
}
