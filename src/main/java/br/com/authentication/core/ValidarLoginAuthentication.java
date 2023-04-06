package br.com.authentication.core;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.authentication.dto.request.LoginDTORequest;
import br.com.authentication.entity.Usuario;
import br.com.authentication.exception.EmailOuSenhaInvalidoException;
import br.com.authentication.repository.UsuarioRepository;
import br.com.authentication.utils.JwtTokenUtil;

@Service
public class ValidarLoginAuthentication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ConvertModalMapper convertModalMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public void validarLoginViaToken(LoginDTORequest login, HttpServletRequest request) {
		Usuario usuario = consultar(login.getEmail());
		if (login.getEmail().equals(usuario.getEmail())
				&& !bCryptPasswordEncoder.matches(login.getSenha(), usuario.getSenha())) {
			throw new EmailOuSenhaInvalidoException();
		}
		if (!login.getEmail().equals(usuario.getEmail())
				&& bCryptPasswordEncoder.matches(login.getSenha(), usuario.getSenha())) {
			throw new EmailOuSenhaInvalidoException();
		}
		if (login.getEmail().equals(usuario.getEmail())
				&& bCryptPasswordEncoder.matches(login.getSenha(), usuario.getSenha())) {
			usuario.setDataUltimoAcesso(LocalDateTime.now());
			usuario.setToken(jwtTokenUtil.generateToken(usuario));
			usuario.setDataModificacao(LocalDateTime.now());
			usuario.setDataUltimoAcesso(LocalDateTime.now());
			convertModalMapper.convertParaResponseDTO(usuarioRepository.save(usuario));
		}
	}

	private Usuario consultar(String email) {
		Usuario user = usuarioRepository.findByEmail(email);
		if (user != null) {
			return user;
		}
		throw new EmailOuSenhaInvalidoException();
	}
}
