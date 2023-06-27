package com.proyecto.turismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//@Table(name="tb_puntos")
public class Puntos {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_punto")
	private Integer id;
	
	//@Column(name="punto")
	private String punto;
	
	public Puntos() {
		
	}

	public Puntos(Integer id, String punto) {
		super();
		this.id = id;
		this.punto = punto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPunto() {
		return punto;
	}

	public void setPunto(String punto) {
		this.punto = punto;
	}
	
}
