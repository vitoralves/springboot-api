package com.springbootapi.test.services;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springbootapi.entities.Usuario;
import com.springbootapi.repositories.UsuarioRepository;
import com.springbootapi.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@MockBean
	private UsuarioRepository repository;

	@Autowired
	private UsuarioService service;
	
	private static final String USUARIO = "User";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.repository.findByUsuarioEquals(Mockito.anyString())).willReturn(Optional.of(new Usuario()));	
	}

	@Test
	public void testFindByUsuario() {
		Optional<Usuario> u = this.service.findByUsuario(USUARIO);
		assertTrue(u.isPresent());
	}

}