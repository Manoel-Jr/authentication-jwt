package br.com.authentication.exception;

public class TokenHeaderErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenHeaderErrorException(String message) {
		super(message);
	}

	public TokenHeaderErrorException() {

	}
}
