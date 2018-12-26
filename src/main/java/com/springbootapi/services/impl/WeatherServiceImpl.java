package com.springbootapi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springbootapi.entities.Weather;
import com.springbootapi.repositories.WeatherRepository;
import com.springbootapi.services.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepository repository;
	
	@Override
	public Optional<Weather> findByClienteId(Long id) {
		return repository.findByClienteId(id);
	}

	@Override
	public Page<Weather> findAll(PageRequest page) {
		return repository.findAll(page);
	}

	@Override
	public Weather save(Weather c) {
		return repository.save(c);
	}
}
