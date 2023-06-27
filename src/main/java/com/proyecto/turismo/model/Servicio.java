package com.proyecto.turismo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//@Table(name="tb_servicio")
public class Servicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_servicio")
	private Integer id;
	
	//@Column(name="servicio")
		private String servicio;
	
		public Servicio() {
			
		}

		public Servicio(Integer id, String servicio) {
			super();
			this.id = id;
			this.servicio = servicio;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getServicio() {
			return servicio;
		}

		public void setServicio(String servicio) {
			this.servicio = servicio;
		}
		
}
