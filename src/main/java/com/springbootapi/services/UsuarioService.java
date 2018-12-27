package com.springbootapi.services;

import java.util.Optional;

import com.springbootapi.entities.Usuario;

public interface UsuarioService {
	
	Optional<Usuario> findByUsuario(String u);
	
}
