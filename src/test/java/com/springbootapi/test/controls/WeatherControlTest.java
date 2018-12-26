package com.springbootapi.test.controls;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootapi.dtos.WeatherDTO;
import com.springbootapi.entities.Cliente;
import com.springbootapi.entities.Weather;
import com.springbootapi.services.WeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WeatherControlTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private WeatherService service;

	private static final String URL = "/api/weather";
	private static final Long ID = 1L;
	private static final Long CLIENTEID = 2L;
	private static final Double MAXTEMP = 50.0;
	private static final Double MINTEMP = 20.56;

	@Test
	public void testFindByClienteId() throws Exception {
		Weather c = this.getWeather();
		BDDMockito.given(this.service.findByClienteId(Mockito.anyLong())).willReturn(Optional.of(c));

		mvc.perform(MockMvcRequestBuilders.get(URL + "/cliente/" + CLIENTEID).content(this.getJsonPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID)).andExpect(jsonPath("$.data.maxTemp").value(MAXTEMP))
				.andExpect(jsonPath("$.data.minTemp").value(MINTEMP))
				.andExpect(jsonPath("$.data.cliente").value(CLIENTEID)).andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testFindByClienteIdInvalid() throws Exception {
		BDDMockito.given(this.service.findByClienteId(Mockito.anyLong())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.get(URL + "/cliente/" + CLIENTEID).content(this.getJsonPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Nada encontrado para cliente " + CLIENTEID));
	}

	@Test
	public void testFindAll() throws Exception {
		Weather c = this.getWeather();
		List<Weather> l = new ArrayList<>();
		l.add(c);
		BDDMockito.given(this.service.findAll(Mockito.any(PageRequest.class))).willReturn(new PageImpl<Weather>(l));

		mvc.perform(MockMvcRequestBuilders.get(URL).content(this.getJsonPost()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.content[0].id").value(ID))
				.andExpect(jsonPath("$.data.content[0].maxTemp").value(MAXTEMP))
				.andExpect(jsonPath("$.data.content[0].minTemp").value(MINTEMP))
				.andExpect(jsonPath("$.data.content[0].cliente").value(CLIENTEID))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	private String getJsonPost() throws JsonProcessingException {
		WeatherDTO dto = new WeatherDTO();
		dto.setId(ID);
		dto.setMaxTemp(MAXTEMP);
		dto.setMinTemp(MINTEMP);
		dto.setCliente(CLIENTEID);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

	private Weather getWeather() {
		Weather c = new Weather();
		c.setId(ID);
		c.setMaxTemp(MAXTEMP);
		c.setMinTemp(MINTEMP);
		c.setCliente(new Cliente());
		c.getCliente().setId(CLIENTEID);

		return c;
	}
}
