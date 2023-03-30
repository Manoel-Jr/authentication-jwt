package br.com.authentication.exception.handler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.authentication.exception.EmailExistenteException;
import br.com.authentication.exception.EmailInvalidoException;
import br.com.authentication.exception.EmailOuSenhaInvalidoException;
import br.com.authentication.exception.SenhaIncorretaException;
import br.com.authentication.exception.TokenHeaderErrorException;
import br.com.authentication.exception.TokenInvalidoException;
import br.com.authentication.exception.UsuarioNuloException;
import br.com.authentication.utils.Mensagens;

@ControllerAdvice
public class ControllerHandlerAdvice {

	@ExceptionHandler(EmailExistenteException.class)
	public ResponseEntity<ApiError> emailExistente(EmailExistenteException ex, HttpServletRequest request) {
		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), Mensagens.EMAIL_JÁ_EXISTE,
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(EmailOuSenhaInvalidoException.class)
	public ResponseEntity<ApiError> usuarioInvalido(EmailOuSenhaInvalidoException ex, HttpServletRequest request) {
		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				Mensagens.EMAIL_OU_SENHA_INCORRETO, request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(EmailInvalidoException.class)
	public ResponseEntity<ApiError> emailInvalido(EmailInvalidoException ex, HttpServletRequest request) {
		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				Mensagens.EMAIL_INVALIDO, request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler(SenhaIncorretaException.class)
	public ResponseEntity<ApiError> senhaIncorreta(SenhaIncorretaException ex, HttpServletRequest request) {
		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), Mensagens.SENHA_INCORRETA,
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(UsuarioNuloException.class)
	public ResponseEntity<ApiError> UsuarioNulo(UsuarioNuloException ex, HttpServletRequest request) {
		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), Mensagens.USUARIO_NULO,
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(TokenHeaderErrorException.class)
	public ResponseEntity<ApiError> tokenError(TokenHeaderErrorException ex, HttpServletRequest request) {
		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				Mensagens.NAO_AUTORIZADO, request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(TokenInvalidoException.class)
	public ResponseEntity<ApiError> tokenIvalido(TokenInvalidoException ex, HttpServletRequest request) {
		ApiError error = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				Mensagens.TOKEN_NAO_PERTENCE_AO_USUARIO, request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
