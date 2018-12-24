package com.springbootpai.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6119959666206475850L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100)
	private String nome;
	@Column(nullable = false)
	private Integer idade;
}
