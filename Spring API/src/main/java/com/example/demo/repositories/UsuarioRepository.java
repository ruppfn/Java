package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Optional<Usuario> findById(String id);
	
	@SuppressWarnings("unchecked")
	Usuario save(Usuario user);
	
	void delete(Usuario user);
}
