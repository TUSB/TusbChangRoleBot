/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 commandExceptionType.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Core.Const.Enums;

public enum CommandExceptionType {
	PERMISSIONS_DENIED("製作者のみ使用可能です");

	//TODO ErrorMessage

	String errorMessage;

	CommandExceptionType() {
	}

	CommandExceptionType(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
