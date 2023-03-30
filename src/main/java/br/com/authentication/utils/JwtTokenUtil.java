package br.com.authentication.utils;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.authentication.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(Usuario usuario) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("subject", usuario.getNome());
		claims.put("email", usuario.getEmail());
		return doGenerateToken(claims);
	}

	private String doGenerateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
