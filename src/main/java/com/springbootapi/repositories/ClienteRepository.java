package com.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapi.entities.Cliente;

@Transactional(readOnly = true)
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
}
