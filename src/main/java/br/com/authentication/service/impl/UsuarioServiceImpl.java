package br.com.authentication.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.authentication.core.ConvertModalMapper;
import br.com.authentication.dto.request.UsuarioRequestDTO;
import br.com.authentication.dto.response.UsuarioResponseDTO;
import br.com.authentication.entity.Telefone;
import br.com.authentication.entity.Usuario;
import br.com.authentication.exception.EmailExistenteException;
import br.com.authentication.repository.UsuarioRepository;
import br.com.authentication.service.UsuarioService;
import br.com.authentication.utils.JwtTokenUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ConvertModalMapper convertModalMapper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuarioRequestDto) {
		if (repository.existsByEmail(usuarioRequestDto.getEmail())) {
			throw new EmailExistenteException();
		}
		List<Telefone> telefones = usuarioRequestDto.getTelefones().stream()
				.map(telefone -> convertModalMapper.converterParaTelefone(telefone)).collect(Collectors.toList());
		Usuario usuario = convertModalMapper.convertParaUsuario(usuarioRequestDto);
		telefones.forEach(telefone -> telefone.setUsuario(usuario));
		usuario.setTelefones(telefones);
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioRequestDto.getSenha()));
		usuario.setDataCriacao(LocalDateTime.now());
		usuario.setToken(jwtTokenUtil.generateToken(usuario));
		return convertModalMapper.convertParaResponseDTO(repository.save(usuario));
	}

}
