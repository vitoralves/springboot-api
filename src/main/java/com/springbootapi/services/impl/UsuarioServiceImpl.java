package com.springbootapi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapi.entities.Usuario;
import com.springbootapi.repositories.UsuarioRepository;
import com.springbootapi.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> findByUsuario(String u) {
		return repository.findByUsuarioEquals(u);
	}

}
