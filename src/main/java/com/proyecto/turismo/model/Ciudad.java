package com.proyecto.turismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
//@Table(name="tb_ciudad")
public class Ciudad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ciudad")
	private long id;
	
	//@Column(name="ciudad")
	private String ciudad;
	
	
	public Ciudad() {
		
	}


	public Ciudad(long id, String ciudad) {
		super();
		this.id = id;
		this.ciudad = ciudad;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}




}
