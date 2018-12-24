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
import com.springbootapi.dtos.ClienteDTO;
import com.springbootapi.entities.Cliente;
import com.springbootapi.services.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControlTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClienteService service;

	private static final String URL = "/api/cliente";
	private static final Long ID = 1L;
	private static final String NOME = "Nome Cliente";
	private static final Integer IDADE = 30;

	@Test
	public void testUpdate() throws Exception {
		Cliente c = this.getCliente();
		BDDMockito.given(this.service.save(Mockito.any(Cliente.class))).willReturn(c);
		BDDMockito.given(this.service.findById(Mockito.anyLong())).willReturn(Optional.of(new Cliente()));

		mvc.perform(MockMvcRequestBuilders.put(URL).content(this.getJsonPost()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID)).andExpect(jsonPath("$.data.nome").value(NOME))
				.andExpect(jsonPath("$.data.idade").value(IDADE)).andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testUpdateClienteInexistent() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put(URL).content(this.getJsonPost()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Cliente de id " + ID + " não encontrado."));
	}

	@Test
	public void testGetById() throws Exception {
		Cliente c = this.getCliente();
		BDDMockito.given(this.service.findById(Mockito.anyLong())).willReturn(Optional.of(c));

		mvc.perform(MockMvcRequestBuilders.get(URL + "/id?cliente=" + ID).content(this.getJsonPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID)).andExpect(jsonPath("$.data.nome").value(NOME))
				.andExpect(jsonPath("$.data.idade").value(IDADE)).andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testGetByIdInexistent() throws Exception {
		BDDMockito.given(this.service.findById(Mockito.anyLong())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.get(URL + "/id?cliente=" + ID).content(this.getJsonPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Cliente de id " + ID + " não encontrado."));
	}

	@Test
	public void testFindAll() throws Exception {
		Cliente c = this.getCliente();
		List<Cliente> l = new ArrayList<>();
		l.add(c);
		BDDMockito.given(this.service.findAll(Mockito.any(PageRequest.class))).willReturn(new PageImpl<Cliente>(l));

		mvc.perform(MockMvcRequestBuilders.get(URL).content(this.getJsonPost()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.content[0].id").value(ID))
				.andExpect(jsonPath("$.data.content[0].nome").value(NOME))
				.andExpect(jsonPath("$.data.content[0].idade").value(IDADE)).andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testDelete() throws JsonProcessingException, Exception {
		BDDMockito.given(this.service.findById(Mockito.anyLong())).willReturn(Optional.of(new Cliente()));

		mvc.perform(MockMvcRequestBuilders.delete(URL + "/" + ID).content(this.getJsonPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testDeleteInexistente() throws JsonProcessingException, Exception {
		BDDMockito.given(this.service.findById(Mockito.anyLong())).willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.delete(URL + "/" + ID).content(this.getJsonPost())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Cliente de id " + ID + " não encontrado."));
	}

	private String getJsonPost() throws JsonProcessingException {
		ClienteDTO dto = new ClienteDTO();
		dto.setId(ID);
		dto.setNome(NOME);
		dto.setIdade(IDADE);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

	private Cliente getCliente() {
		Cliente c = new Cliente();
		c.setId(ID);
		c.setIdade(IDADE);
		c.setNome(NOME);

		return c;
	}
}
