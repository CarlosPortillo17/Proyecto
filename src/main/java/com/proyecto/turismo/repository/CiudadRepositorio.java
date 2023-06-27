package com.proyecto.turismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.turismo.model.Ciudad;


public interface CiudadRepositorio extends JpaRepository<Ciudad, Integer>{

}
