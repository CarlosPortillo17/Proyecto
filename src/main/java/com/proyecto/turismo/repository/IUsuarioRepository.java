package com.proyecto.turismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.turismo.model.Usuario;

public interface IUsuarioRepository  extends JpaRepository<Usuario, Long>{
	Usuario findByUsuario(String usuario);
}
