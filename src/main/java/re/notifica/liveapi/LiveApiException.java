package re.notifica.liveapi;

/**
 * Exceptions to be used by Notificare Live API library
 * @author Joris Verbogt <joris@notifica.re>
 */
public class LiveApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LiveApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public LiveApiException(String message) {
		super(message);
	}

	public LiveApiException() {
		super();
	}
}
