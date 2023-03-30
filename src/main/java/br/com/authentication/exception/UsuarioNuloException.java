package br.com.authentication.exception;

public class UsuarioNuloException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNuloException(String message) {
		super(message);
	}

	public UsuarioNuloException() {

	}
}
