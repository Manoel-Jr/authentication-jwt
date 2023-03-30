package br.com.authentication.service;

import javax.servlet.http.HttpServletRequest;

import br.com.authentication.dto.request.LoginDTORequest;
import br.com.authentication.dto.response.UsuarioResponseDTO;

public interface LoginService {

	public UsuarioResponseDTO login(LoginDTORequest login, HttpServletRequest request);
}
