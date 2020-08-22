package jp.skyblock.Utility.Exception;

public interface ExceptionIf {

	/**
	 * @return ErrorCode
	 */
	ErrorCode getCode();

	/**
	 * @return ErrorRootCause
	 */
	Throwable getCause();

	/**
	 * @return ErrorMessage
	 */
	String getMessage();

	/**
	 * ErrorCode
	 */
	interface ErrorCode {
		int ordinal();

		String name();
	}
}
