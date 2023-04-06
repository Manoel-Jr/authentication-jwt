package br.com.authentication.service;

import javax.servlet.http.HttpServletRequest;

import br.com.authentication.dto.request.UsuarioRequestDTO;
import br.com.authentication.dto.response.UsuarioResponseDTO;

public interface UsuarioService {

	public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuario);
	
	public UsuarioResponseDTO consultarPerfil(String id,HttpServletRequest request);
}
