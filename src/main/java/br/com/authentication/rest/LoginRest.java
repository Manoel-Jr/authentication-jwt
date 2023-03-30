package br.com.authentication.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.authentication.dto.request.LoginDTORequest;
import br.com.authentication.dto.response.UsuarioResponseDTO;
import br.com.authentication.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginRest {

	@Autowired
	private LoginService loginService;

	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> login(@RequestBody LoginDTORequest login,HttpServletRequest request) {
		return new ResponseEntity<>(loginService.login(login,request), HttpStatus.OK);
	}
}
