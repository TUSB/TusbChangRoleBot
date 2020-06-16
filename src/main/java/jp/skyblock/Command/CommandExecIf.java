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
import static jp.skyblock.Utility.ExceptionIf.commandException;

public interface CommandExecIf  {
	/**
	 * Execメソッドを取得
	 *
	 * @param cmdSplit
	 * @return
	 */
	static CommandExecIf getInstance(String[] cmdSplit) {
		CommandExecIf command;

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

	void execute() throws Exception;

	 Object executeResponse(Object obj);

	default Exception sendError(Exception e, String ErrorMessage, MessageReceivedEvent event) throws Exception {
		return commandException(e, ErrorMessage, event);
	}

}
