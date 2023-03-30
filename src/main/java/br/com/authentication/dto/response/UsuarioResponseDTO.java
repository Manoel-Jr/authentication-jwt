package br.com.authentication.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

	private String id;

	private String nome;

	private String email;

	private String senha;

	private List<TelefoneResponseDTO> telefones;

	private String token;

	public LocalDateTime dataCriacao;

	public LocalDateTime dataModificacao;

	public LocalDateTime dataUltimoAcesso;
}
