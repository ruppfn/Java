package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Persona;
import com.example.demo.entities.Usuario;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer>{
	
	List<Persona> findAll();
	Optional<Persona> findById(int id);
	public Optional<Persona> findByFirstName(String firstName);
	
	@SuppressWarnings("unchecked")
	Persona save(Persona p);
	
	void delete(Persona p);
}
