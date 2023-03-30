package br.com.authentication.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

	private String nome;

	private String email;

	private String senha;

	private List<TelefoneRequestDTO> telefones;

}
