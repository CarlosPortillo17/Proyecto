package com.proyecto.turismo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.turismo.model.Hospedaje;


public interface HospedajeRepositorio extends JpaRepository<Hospedaje, Integer>{

	List<Hospedaje> findByPuntosPuntoContaining(String punto);
	
}
