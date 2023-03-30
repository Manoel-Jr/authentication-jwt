package br.com.authentication.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Config {

	@Bean
	public ModelMapper model() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder encryptPassword() {
		return new BCryptPasswordEncoder();
	}
}
