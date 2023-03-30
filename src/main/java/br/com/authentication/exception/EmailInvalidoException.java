package br.com.authentication.exception;

public class EmailInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailInvalidoException(String message) {
		super(message);
	}

	public EmailInvalidoException() {

	}
}
