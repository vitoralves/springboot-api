package com.springbootapi.security;

import com.springbootapi.entities.Usuario;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um usuario
	 *
	 * @param usuario
	 * @return JwtUser
	 */
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getUsuario(), usuario.getSenha());
	}

}
