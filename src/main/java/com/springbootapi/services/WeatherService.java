package com.springbootapi.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.springbootapi.entities.Weather;

public interface WeatherService {
	
	Optional<Weather> findByClienteId(Long cliente);
	
	Page<Weather> findAll(PageRequest page);
	
	Weather save(Weather c);
}
