package com.springbootapi.test.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springbootapi.entities.Cliente;
import com.springbootapi.repositories.ClienteRepository;
import com.springbootapi.services.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
	
	@MockBean
	private ClienteRepository repository;

	@Autowired
	private ClienteService service;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.repository.findAll(Mockito.any(PageRequest.class))).willReturn(new PageImpl<Cliente>(new ArrayList<Cliente>()));
		BDDMockito.given(this.repository.findById(Mockito.anyLong())).willReturn(Optional.of(new Cliente()));
		BDDMockito.given(this.repository.save(Mockito.any(Cliente.class))).willReturn(new Cliente());		
	}

	@Test
	public void testFindAll() {
		Page<Cliente> cliente = this.service.findAll(new PageRequest(0, 30));
		assertNotNull(cliente);
	}
	
	@Test
	public void testFindById() {
		Optional<Cliente> c = this.service.findById(1L);
		assertTrue(c.isPresent());		
	}
	
	@Test
	public void testSave() {
		Cliente cliente = this.service.save(new Cliente());
		assertNotNull(cliente);
	}

}