package br.com.authentication.exception;

public class TokenInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenInvalidoException(String message) {
		super(message);
	}

	public TokenInvalidoException() {

	}
}
