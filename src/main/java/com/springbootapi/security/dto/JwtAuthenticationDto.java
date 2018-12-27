package com.springbootapi.security.dto;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("deprecation")
public class JwtAuthenticationDto {

	private String usuario;
	private String senha;

	public JwtAuthenticationDto() {
	}

	@NotEmpty(message = "Usuário não pode ser vazio.")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@NotEmpty(message = "Senha não pode ser vazia.")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationDto [usuario=" + usuario + ", senha=" + senha + "]";
	}

}
