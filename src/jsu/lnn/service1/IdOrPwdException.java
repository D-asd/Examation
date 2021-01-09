package jsu.lnn.service1;

public class IdOrPwdException extends Exception {

	private static final long serialVersionUID = -8670446601475265575L;

	public IdOrPwdException() {
	}

	public IdOrPwdException(String message) {
		super(message);
	}

	public IdOrPwdException(Throwable cause) {
		super(cause);
	}

	public IdOrPwdException(String message, Throwable cause) {
		super(message, cause);
	}

}
