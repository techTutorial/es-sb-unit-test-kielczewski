package es.sb.utest.example.service.exception;

public class EsUserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EsUserAlreadyExistsException(String message) {
        super(message);
    }
	
}
