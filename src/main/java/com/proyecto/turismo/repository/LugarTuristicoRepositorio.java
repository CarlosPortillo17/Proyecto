package com.proyecto.turismo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.turismo.model.LugarTuristico;

public interface LugarTuristicoRepositorio extends JpaRepository<LugarTuristico, Integer> {

	Page<LugarTuristico> findByCiudades_CiudadContainingIgnoreCase(String ciudad, Pageable pageable);
	
}
