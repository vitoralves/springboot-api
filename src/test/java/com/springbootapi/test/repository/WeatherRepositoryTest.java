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

import com.springbootapi.entities.Cliente;
import com.springbootapi.entities.Weather;
import com.springbootapi.repositories.ClienteRepository;
import com.springbootapi.repositories.WeatherRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WeatherRepositoryTest {
	
		@Autowired
		public WeatherRepository repository;
		@Autowired
		public ClienteRepository clienteRepository;
		
		private static Long CLIENTEID = 1L;
		private static final String NOME = "Nome de Teste";
		private static final Integer IDADE = 29;
		private static final Double MAXTEMP = 50.0;
		private static final Double MINTEMP = 20.56;

		@Before
		public void setUp() throws Exception {
			Cliente c = clienteRepository.save(getCliente());
			CLIENTEID = c.getId();
			repository.save(getWeather(c));
		}

		@After
		public final void tearDown() {
			repository.deleteAll();
			clienteRepository.deleteAll();
		}
		
		/**
		 * Testa método que busca Weather através de um cliente
		 */
		@Test
		public void testfindByClienteId() {
			Optional<Weather> w = repository.findByClienteId(CLIENTEID);
			assertTrue(w.isPresent());
		}
		
		/**
		 * Testa a busca de Weathers por cliente inexistente
		 */
		@Test
		public void testFindByClienteIdInvalid() {
			Optional<Weather> w = repository.findByClienteId(10L);
			assertFalse(w.isPresent());
		}
		
		private Weather getWeather(Cliente c) {
			Weather w = new Weather();
			w.setMaxTemp(MAXTEMP);
			w.setMinTemp(MINTEMP);
			w.setCliente(c);
			return w;
		}
		
		private Cliente getCliente() {
			Cliente c = new Cliente();
			c.setIdade(IDADE);
			c.setNome(NOME);
			return c;
		}
}