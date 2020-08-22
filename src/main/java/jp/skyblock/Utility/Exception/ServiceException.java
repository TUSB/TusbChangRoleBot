package jp.skyblock.Utility.Exception;

public class ServiceException extends RuntimeException implements ExceptionIf {

	private final ErrorCode code;

	public ServiceException(ErrorCode code, Exception ex) {
		super(ex);
		this.code = code;
	}

	public ServiceException(ErrorCode code, String message, Exception ex) {
		super(message, ex);
		this.code = code;
	}

	public ServiceException(ErrorCode code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(ErrorCode code) {
		super();
		this.code = code;
	}

	@Override
	public ErrorCode getCode() {
		return code;
	}
}
