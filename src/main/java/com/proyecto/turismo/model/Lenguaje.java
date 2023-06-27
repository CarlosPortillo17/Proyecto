package com.proyecto.turismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
//@Table(name="tb_lenguaje")
public class Lenguaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_lenguaje")
	private long id;
	
	//@Column(name="lenguaje")
	private String lenguaje;
	
	public Lenguaje() {
		
	}

	public Lenguaje(long id, String lenguaje) {
		super();
		this.id = id;
		this.lenguaje = lenguaje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	
}
