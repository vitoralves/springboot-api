package com.springbootapi.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ClienteDTO {
	private Long id;
	@Length(min = 3, max = 100, message = "O nome deve ser composto de no mínimo 3 e no máximo 100 caracteres")
	private String nome;
	@NotNull(message = "Informe a idade")
	private Integer idade;
}
