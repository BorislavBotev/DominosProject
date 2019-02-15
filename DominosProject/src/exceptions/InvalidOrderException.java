package exceptions;

public class InvalidOrderException extends Exception {

	private static final long serialVersionUID = -752748794140123648L;

	public InvalidOrderException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidOrderException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public InvalidOrderException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InvalidOrderException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InvalidOrderException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}