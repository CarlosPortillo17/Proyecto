package com.proyecto.turismo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.turismo.model.Guia;


public interface GuiaRepositorio extends JpaRepository<Guia, Integer>{
	
	Page<Guia> findByNacionalidades_NacionalidadContainingIgnoreCase(String nacionalidad, Pageable pageable);
}
