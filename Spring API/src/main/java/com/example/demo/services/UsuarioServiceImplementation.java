package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

public class UsuarioServiceImplementation implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public Usuario add(Usuario user) {
		return repository.save(user);
	}

	@Override
	public void delete(Usuario user) {
		repository.delete(user);
		
	}

	@Override
	public Optional<Usuario> findById(String username) {
		return repository.findById(username);
	}

}
