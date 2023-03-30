package br.com.authentication.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.authentication.shared.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
	private List<Telefone> telefones;

	@Column(name = "token")
	private String token;

	@PrePersist
	public void persistir() {
		this.id = UUID.randomUUID().toString();
	}
}
