package br.com.authentication.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.authentication.exception.EmailExistenteException;
import br.com.authentication.exception.EmailInvalidoException;
import br.com.authentication.exception.EmailOuSenhaInvalidoException;
import br.com.authentication.exception.SenhaIncorretaException;
import br.com.authentication.exception.SessaoInvalidaException;
import br.com.authentication.exception.TokenHeaderErrorException;
import br.com.authentication.exception.TokenInvalidoException;
import br.com.authentication.exception.UsuarioNuloException;
import br.com.authentication.utils.Mensagens;

@ControllerAdvice
public class ControllerHandlerAdvice {

	@ExceptionHandler(EmailExistenteException.class)
	public ResponseEntity<ApiError> emailExistente(EmailExistenteException ex, HttpServletRequest request) {
		ApiError error = new ApiError(Mensagens.EMAIL_JÁ_EXISTE);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(EmailOuSenhaInvalidoException.class)
	public ResponseEntity<ApiError> usuarioInvalido(EmailOuSenhaInvalidoException ex, HttpServletRequest request) {
		ApiError error = new ApiError(Mensagens.EMAIL_OU_SENHA_INVALIDOS);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(EmailInvalidoException.class)
	public ResponseEntity<ApiError> emailInvalido(EmailInvalidoException ex, HttpServletRequest request) {
		ApiError error = new ApiError(Mensagens.EMAIL_INVALIDO);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler(SenhaIncorretaException.class)
	public ResponseEntity<ApiError> senhaIncorreta(SenhaIncorretaException ex, HttpServletRequest request) {
		ApiError error = new ApiError(Mensagens.SENHA_INCORRETA);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(UsuarioNuloException.class)
	public ResponseEntity<ApiError> UsuarioNulo(UsuarioNuloException ex, HttpServletRequest request) {
		ApiError error = new ApiError(Mensagens.USUARIO_NULO);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(TokenHeaderErrorException.class)
	public ResponseEntity<ApiError> tokenError(TokenHeaderErrorException ex, HttpServletRequest request) {
		ApiError error = new ApiError(Mensagens.NAO_AUTORIZADO);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(TokenInvalidoException.class)
	public ResponseEntity<ApiError> tokenIvalido(TokenInvalidoException ex, HttpServletRequest request) {
		ApiError error = new ApiError(Mensagens.TOKEN_NAO_PERTENCE_AO_USUARIO);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(SessaoInvalidaException.class)
	public ResponseEntity<ApiError> sessaoInvalida(SessaoInvalidaException ex, HttpServletRequest request) {
		ApiError error = new ApiError(Mensagens.SESSAO_INVALIDA);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
