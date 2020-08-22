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

import jp.skyblock.Utility.Exception.ExceptionIf;

public enum DatabaseError implements ExceptionIf.ErrorCode {
	DatabaseInit,
	DatabaseState,
	DatabaseTransaction,
	;
}
