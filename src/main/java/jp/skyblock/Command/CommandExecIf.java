/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 CommandExecIf.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Command;

import jp.skyblock.Utility.ExceptionIf;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static jp.skyblock.Core.Const.Constant.COMMAND_HELLO;
import static jp.skyblock.Core.Const.Constant.COMMAND_ROLE;
import static jp.skyblock.Core.Const.Constant.PREFIX;

public interface CommandExecIf {
	/**
	 * Execメソッドを取得
	 *
	 * @param msg
	 * @return
	 */
	static CommandExecIf getCommandExec(String msg) {
		CommandExecIf command;
		String[] cmdSplit = msg.split(" ");
		CommandEvent.cmdParam = cmdSplit;

		switch (cmdSplit[0]) {
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

	default void execute() throws ExceptionIf {

	}

	default Object executeResponse(Object obj) {
		return obj;
	}

	/**
	 * @return event
	 */
	class CommandEvent {
		protected static String[] cmdParam;
		protected static MessageReceivedEvent event;

		public CommandEvent() {
		}

		protected static MessageReceivedEvent getEvent() {
			return event;
		}

		public static void setEvent(MessageReceivedEvent event) {
			CommandEvent.event = event;
		}
	}
}
