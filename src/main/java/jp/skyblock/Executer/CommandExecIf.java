package jp.skyblock.Executer;

import jp.skyblock.Command.DefaultCommand;
import jp.skyblock.Command.PingCommand;
import jp.skyblock.Command.RoleCommand;
import jp.skyblock.Utility.ExceptionIf;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static jp.skyblock.Core.Constant.COMMAND_HELLO;
import static jp.skyblock.Core.Constant.COMMAND_ROLE;
import static jp.skyblock.Core.Constant.PREFIX;

public interface CommandExecIf {
	/**
	 * Execメソッドを取得
	 * @param msg
	 * @return
	 */
	static CommandExecIf getCommandExec(String msg){
		CommandExecIf command;
		switch(msg){
			case PREFIX + COMMAND_HELLO:
				command = new PingCommand();
				break;
			case PREFIX + COMMAND_ROLE:
				command = new RoleCommand();
				break;
			default:
				command = new DefaultCommand();
				break;
		}
		return command;
	}


	/**
	 * @return event
	 */
	class CommandEvent {
		public CommandEvent() {
		}

		public static MessageReceivedEvent getEvent() {
			return event;
		}

		public static void setEvent(MessageReceivedEvent event) {
			CommandEvent.event = event;
		}

		private static MessageReceivedEvent event;
	}

	MessageReceivedEvent event = CommandEvent.getEvent();
	CommandEvent commandEvent = new CommandEvent();
	default void execute() throws ExceptionIf {

	}

	default Object executeResponse(Object obj){
		return obj;
	}
}
