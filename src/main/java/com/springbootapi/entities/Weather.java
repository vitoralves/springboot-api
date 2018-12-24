package com.springbootapi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Weather implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2349647142746001656L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "max_temp")
	private Double maxTemp;
	@Column(name = "min_temp")
	private Double minTemp;
	@JoinColumn(name = "cliente", referencedColumnName = "id")
	@ManyToOne()
	private Cliente cliente;
}
