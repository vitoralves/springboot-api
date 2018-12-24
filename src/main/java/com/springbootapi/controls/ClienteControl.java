package com.springbootapi.controls;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootapi.dtos.ClienteDTO;
import com.springbootapi.entities.Cliente;
import com.springbootapi.response.Response;
import com.springbootapi.services.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteControl {

	@Autowired
	private ClienteService service;

	private static final Logger log = LoggerFactory.getLogger(ClienteControl.class);
	
	/**
	 * Retorna todos clientes cadastrados
	 * @param page qual página o cliente deseja consultar, default página 0
	 * @param size quantos registros serão exibidos por página, default 30
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<Response<Page<ClienteDTO>>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "30") int size) {
		
		log.info("Buscando todos clientes cadastrados");
		Response<Page<ClienteDTO>> response = new Response<Page<ClienteDTO>>();
		
		PageRequest pageRequest = new PageRequest(page, size);
		Page<Cliente> c = service.findAll(pageRequest);
		
		Page<ClienteDTO> dto = c.map(m -> this.convertEntityToDto(m));
		
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna um cliente passando o id do cliente, caso não seja encontrado devolve um 400 com a mensagem de não encontrado
	 * @param id
	 * @return
	 */
	@GetMapping(path = "id")
	public ResponseEntity<Response<ClienteDTO>> get(@RequestParam(value = "id") Long id) {

		log.info("Buscando cliente de id " + id);
		Response<ClienteDTO> response = new Response<ClienteDTO>();

		Optional<Cliente> c = service.findById(id);

		if (!c.isPresent()) {
			response.getErrors().add("Cliente de id " + id + " não encontrado.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertEntityToDto(c.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Remove um cliente passando o id do cliente, caso não encontrado devolve 400
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deleteById(@PathVariable("id") Long id) {
		log.info("Removendo cliente "+id);
		
		Optional<Cliente> c = service.findById(id);
		Response<String> response = new Response<String>();
		
		if(!c.isPresent()) {
			response.getErrors().add("Cliente de id "+id+ " não encontrado.");
			return ResponseEntity.badRequest().body(response);
		}
		
		service.remove(id);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Atualiza um cliente e retorna o novo objeto no corpo da resposta
	 * @param dto
	 * @param result
	 * @return
	 */
	@PutMapping()
	public ResponseEntity<Response<ClienteDTO>> update(@Valid @RequestBody ClienteDTO dto, BindingResult result){
		
		Response<ClienteDTO> response = new Response<ClienteDTO>();
		
		Optional<Cliente> c = service.findById(dto.getId());		
		if (!c.isPresent()) {
			result.addError(new ObjectError("Cliente", "Cliente de id "+dto.getId()+" não encontrado."));
		}
		
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(response);
		}
		
		Cliente cli = service.save(this.convertDtoToEntity(dto));
		response.setData(this.convertEntityToDto(cli));
		
		return ResponseEntity.ok(response);
	}

	private Cliente convertDtoToEntity(ClienteDTO dto) {
		Cliente c = new Cliente();
		c.setId(dto.getId());
		c.setIdade(dto.getIdade());
		c.setNome(dto.getNome());

		return c;
	}

	private ClienteDTO convertEntityToDto(Cliente c) {
		ClienteDTO dto = new ClienteDTO();
		dto.setId(c.getId());
		dto.setIdade(c.getIdade());
		dto.setNome(c.getNome());

		return dto;
	}
}