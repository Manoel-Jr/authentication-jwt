package br.com.authentication.exception;

public class EmailOuSenhaInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailOuSenhaInvalidoException(String message) {
		super(message);
	}

	public EmailOuSenhaInvalidoException() {

	}
}
