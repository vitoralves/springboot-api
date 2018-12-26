package com.springbootapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapi.entities.Weather;

@Transactional(readOnly = true)
public interface WeatherRepository extends JpaRepository<Weather, Long>{
	
	Optional<Weather> findByClienteId(Long id);
}
