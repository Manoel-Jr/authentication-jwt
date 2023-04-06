package br.com.authentication.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.authentication.core.ConvertModalMapper;
import br.com.authentication.core.ValidarLoginAuthentication;
import br.com.authentication.dto.request.LoginDTORequest;
import br.com.authentication.dto.response.UsuarioResponseDTO;
import br.com.authentication.entity.Usuario;
import br.com.authentication.exception.EmailOuSenhaInvalidoException;
import br.com.authentication.repository.UsuarioRepository;
import br.com.authentication.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ConvertModalMapper convertModalMapper;

	@Autowired
	private ValidarLoginAuthentication validarTokenAuthentication;

	public UsuarioResponseDTO login(LoginDTORequest login, HttpServletRequest request) {
		Usuario usuario = consultar(login.getEmail());
		validarTokenAuthentication.validarLoginViaToken(login, request);
		return convertModalMapper.convertParaResponseDTO(usuarioRepository.save(usuario));
	}

	private Usuario consultar(String email) {
		Usuario user = usuarioRepository.findByEmail(email);
		if (user != null) {
			return user;
		}
		throw new EmailOuSenhaInvalidoException();
	}

}
