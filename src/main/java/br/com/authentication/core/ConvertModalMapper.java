package br.com.authentication.core;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.authentication.dto.request.LoginDTORequest;
import br.com.authentication.dto.request.TelefoneRequestDTO;
import br.com.authentication.dto.request.UsuarioRequestDTO;
import br.com.authentication.dto.response.UsuarioResponseDTO;
import br.com.authentication.entity.Telefone;
import br.com.authentication.entity.Usuario;

@Service
public class ConvertModalMapper {

	@Autowired
	private ModelMapper mapper;

	public UsuarioResponseDTO convertParaResponseDTO(Usuario user) {
		UsuarioResponseDTO usuario = mapper.map(user, UsuarioResponseDTO.class);
		return usuario;
	}

	public Usuario convertParaUsuario(UsuarioRequestDTO request) {
		Usuario usuario = mapper.map(request, Usuario.class);
		return usuario;
	}

	public Telefone converterParaTelefone(TelefoneRequestDTO request) {
		request = TelefoneRequestDTO.builder()
				.ddd(request.getDdd())
				.numero(request.getNumero())
				.build();
		 return mapper.map(request, Telefone.class);
	}
	
	public Usuario converterLogin(LoginDTORequest login) {
		Usuario usuario = mapper.map(login, Usuario.class);
		return usuario;
	}
}
