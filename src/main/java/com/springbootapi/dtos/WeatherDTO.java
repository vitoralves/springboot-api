package com.springbootapi.dtos;

import javax.validation.constraints.NotNull;

import com.springbootapi.entities.Cliente;

import lombok.Data;

@Data
public class WeatherDTO {
	
	private Long id;
	@NotNull(message = "Informe a temperatura máxima")
	private Double maxTemp;
	@NotNull(message = "Informe a temperatura mínima")
	private Double minTemp;
	@NotNull(message = "Informe o cliente")
	private Cliente cliente;
}
