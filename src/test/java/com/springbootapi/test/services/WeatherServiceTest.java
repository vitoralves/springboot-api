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

import com.springbootapi.entities.Weather;
import com.springbootapi.repositories.WeatherRepository;
import com.springbootapi.services.WeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WeatherServiceTest {
	
	@MockBean
	private WeatherRepository repository;

	@Autowired
	private WeatherService service;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.repository.findAll(Mockito.any(PageRequest.class))).willReturn(new PageImpl<Weather>(new ArrayList<Weather>()));
		BDDMockito.given(this.repository.findByClienteId(Mockito.anyLong())).willReturn(Optional.of(new Weather()));
		BDDMockito.given(this.repository.save(Mockito.any(Weather.class))).willReturn(new Weather());		
	}

	@Test
	public void testFindAll() {
		Page<Weather> Weather = this.service.findAll(new PageRequest(0, 30));
		assertNotNull(Weather);
	}
	
	@Test
	public void testFindByClienteId() {
		Optional<Weather> c = this.service.findByClienteId(1L);
		assertTrue(c.isPresent());		
	}
	
	@Test
	public void testSave() {
		Weather Weather = this.service.save(new Weather());
		assertNotNull(Weather);
	}

}