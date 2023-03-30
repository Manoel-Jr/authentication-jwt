package br.com.authentication.core;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.authentication.dto.request.LoginDTORequest;
import br.com.authentication.entity.Usuario;
import br.com.authentication.exception.TokenHeaderErrorException;
import br.com.authentication.exception.TokenInvalidoException;
import br.com.authentication.repository.UsuarioRepository;
import br.com.authentication.utils.JwtTokenUtil;

@Service
public class ValidarTokenAuthentication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ConvertModalMapper convertModalMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public void validarLoginViaToken(LoginDTORequest login, HttpServletRequest request) {
		Usuario usuario = usuarioRepository.findByEmail(login.getEmail());
		String token = request.getHeader("Authorization");
		if (StringUtils.isBlank(token)) {
			throw new TokenHeaderErrorException();
		}
		if (!usuario.getToken().equals(token)) {
			throw new TokenInvalidoException();
		}
		if (usuario.getToken().equals(token)) {
			if (login.getEmail().equals(usuario.getEmail())
					&& bCryptPasswordEncoder.matches(login.getSenha(), usuario.getSenha())) {
				usuario.setDataUltimoAcesso(LocalDateTime.now());
				usuario.setToken(jwtTokenUtil.generateToken(usuario));
				usuario.setDataModificacao(LocalDateTime.now());
				convertModalMapper.convertParaResponseDTO(usuarioRepository.save(usuario));
			}
		}
	}

}
