package com.example.demo.services;

import java.util.Optional;

import com.example.demo.entities.Usuario;

public interface UsuarioService {
	
	Usuario add(Usuario user);
	void delete(Usuario user);
	Optional<Usuario> findById(String username);
}
