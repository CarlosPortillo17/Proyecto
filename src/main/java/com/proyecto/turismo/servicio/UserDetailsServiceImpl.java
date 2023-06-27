package com.proyecto.turismo.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.proyecto.turismo.model.Usuario;
import com.proyecto.turismo.repository.IUsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username);
         
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new MyUserDetails(usuario);
    }
}