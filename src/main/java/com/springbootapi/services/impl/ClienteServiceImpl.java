package com.springbootapi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springbootapi.entities.Cliente;
import com.springbootapi.repositories.ClienteRepository;
import com.springbootapi.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public Optional<Cliente> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<Cliente> findAll(PageRequest page) {
		return repository.findAll(page);
	}

	@Override
	public Cliente save(Cliente c) {
		return repository.save(c);
	}

	@Override
	public void remove(Long id) {
		repository.deleteById(id);		
	}

}
