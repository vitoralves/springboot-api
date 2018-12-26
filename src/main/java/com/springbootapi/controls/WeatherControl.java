package com.springbootapi.controls;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootapi.dtos.WeatherDTO;
import com.springbootapi.entities.Weather;
import com.springbootapi.response.Response;
import com.springbootapi.services.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherControl {

	@Autowired
	private WeatherService service;

	private static final Logger log = LoggerFactory.getLogger(WeatherControl.class);

	/**
	 * Busca todos as informações salvas
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<Response<Page<WeatherDTO>>> getAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "30") int size) {

		Response<Page<WeatherDTO>> response = new Response<Page<WeatherDTO>>();
		PageRequest pageRequest = new PageRequest(page, size);

		log.info("Buscando todos Weathers cadastrados");
		Page<Weather> result = service.findAll(pageRequest);

		Page<WeatherDTO> dto = result.map(m -> this.convertEntityToDto(m));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path = "cliente/{id}")
	public ResponseEntity<Response<WeatherDTO>> getByCliente(@PathVariable("id") Long cliente) {
		
		log.info("Buscando todas informações do cliente "+cliente);
		Response<WeatherDTO> response = new Response<WeatherDTO>();
		
		Optional<Weather> opt = service.findByClienteId(cliente);
		
		if (!opt.isPresent()) {
			response.getErrors().add("Nada encontrado para cliente "+cliente);
			return ResponseEntity.badRequest().body(response);
		}
		
		WeatherDTO dto = this.convertEntityToDto(opt.get());
		response.setData(dto);
		return ResponseEntity.ok(response);
	}

	private WeatherDTO convertEntityToDto(Weather c) {
		WeatherDTO dto = new WeatherDTO();
		dto.setId(c.getId());
		dto.setCliente(c.getCliente().getId());
		dto.setMaxTemp(c.getMaxTemp());
		dto.setMinTemp(c.getMinTemp());

		return dto;
	}
}
