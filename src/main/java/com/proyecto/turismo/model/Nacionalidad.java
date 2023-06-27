package com.proyecto.turismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//@Table(name="tb_nacionalidades")
public class Nacionalidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_nacionalidad")
	private Integer id;
	
	//@Column(name="nacionalidad")
	private String nacionalidad;
	
	public Nacionalidad() {
		
	}

	public Nacionalidad(Integer id, String nacionalidad) {
		super();
		this.id = id;
		this.nacionalidad = nacionalidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	
}
