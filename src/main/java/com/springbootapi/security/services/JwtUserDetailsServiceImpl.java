package com.springbootapi.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springbootapi.entities.Usuario;
import com.springbootapi.security.JwtUserFactory;
import com.springbootapi.services.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Optional<Usuario> u = service.findByUsuario(usuario);

		if (u.isPresent()) {
			return JwtUserFactory.create(u.get());
		}

		throw new UsernameNotFoundException("Usuário não encontrado.");
	}

}
