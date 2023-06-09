package br.com.authentication.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.authentication.dto.request.UsuarioRequestDTO;
import br.com.authentication.dto.response.UsuarioResponseDTO;
import br.com.authentication.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRest {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO usuario) {
		return new ResponseEntity<>(usuarioService.cadastrar(usuario), HttpStatus.CREATED);
	}

	@PostMapping("/consultar/{id}")
	public ResponseEntity<UsuarioResponseDTO> retornar(@PathVariable String id, HttpServletRequest request) {
		return new ResponseEntity<>(usuarioService.consultarPerfil(id, request), HttpStatus.OK);
	}
}
