package com.springbootapi.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ClienteDTO {
	private Long id;
	@Length(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
	private String nome;
	@NotNull(message = "Informe a idade.")
	private Integer idade;
}
