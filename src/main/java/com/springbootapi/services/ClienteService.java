package com.springbootapi.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.springbootapi.entities.Cliente;

public interface ClienteService {
	
	Optional<Cliente> findById(Long id);
	
	Page<Cliente> findAll(PageRequest page);
	
	Cliente save(Cliente c);
	
	void remove(Long id);
}
