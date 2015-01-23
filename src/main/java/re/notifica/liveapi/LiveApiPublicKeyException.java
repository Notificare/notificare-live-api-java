package re.notifica.liveapi;

/**
 * Exceptions to be used by Notificare Live API library
 * @author Joris Verbogt <joris@notifica.re>
 */
public class LiveApiPublicKeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LiveApiPublicKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public LiveApiPublicKeyException(String message) {
		super(message);
	}

	public LiveApiPublicKeyException() {
		super();
	}
}
