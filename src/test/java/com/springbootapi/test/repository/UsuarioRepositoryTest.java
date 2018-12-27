package com.springbootapi.test.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springbootapi.entities.Usuario;
import com.springbootapi.repositories.UsuarioRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
		@Autowired
		public UsuarioRepository repository;
		
		private static final String USUARIO = "user";
		private static final String SENHA = "$2a$10$itmCY8RirNutsi1c99jq8uN5BCHlWB.8FC5ScC6KTRq5NvbEA7ADy";

		@Before
		public void setUp() throws Exception {
			repository.save(getUsuario());
		}

		@After
		public final void tearDown() {
			repository.deleteAll();
		}
		
		/**
		 * Testa método que busca Usuario através do nome de usuario
		 */
		@Test
		public void testfindByUsuario() {
			Optional<Usuario> u = repository.findByUsuarioEquals(USUARIO);
			assertTrue(u.isPresent());
		}
		
		/**
		 * Testa a busca de Usuarios por usuario inexistente
		 */
		@Test
		public void testFindByUsuarioInvalid() {
			Optional<Usuario> u = repository.findByUsuarioEquals("INVALID");
			assertFalse(u.isPresent());
		}
		
		private Usuario getUsuario() {
			Usuario u = new Usuario();
			u.setUsuario(USUARIO);
			u.setSenha(SENHA);
			
			return u;
		}
}