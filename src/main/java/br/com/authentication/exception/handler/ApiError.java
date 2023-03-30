package br.com.authentication.exception.handler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

	private LocalDateTime timestemp;

	private Integer status;

	private String error;

	private String path;
}
