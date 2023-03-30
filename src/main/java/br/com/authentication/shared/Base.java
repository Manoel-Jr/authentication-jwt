package br.com.authentication.shared;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@MappedSuperclass
public class Base implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedDate
	@Column(name = "dataCriacao")
	protected LocalDateTime dataCriacao;

	@LastModifiedDate
	@Column(name = "dataModificacao")
	protected LocalDateTime dataModificacao;

	@Column(name = "dataUltimoAcesso")
	protected LocalDateTime dataUltimoAcesso;
}
