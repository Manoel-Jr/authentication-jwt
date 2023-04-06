package br.com.authentication.exception;

public class SessaoInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SessaoInvalidaException(String message) {
		super(message);
	}

	public SessaoInvalidaException() {

	}
}
