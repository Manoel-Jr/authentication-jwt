package br.com.authentication.dto.request;

import lombok.Data;

@Data
public class LoginDTORequest {

	private String email;

	private String senha;
}
