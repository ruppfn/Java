package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Persona;
import com.example.demo.repositories.PersonaRepository;
import com.google.gson.Gson;

@Service
public class PersonaServiceImplementation implements PersonaService{

	@Autowired
	private PersonaRepository repository;

	@Override
	public Iterable<Persona> listar() {
		return repository.findAll();
	}

	@Override
	public Persona add(String json) {
		Gson gson = new Gson();
		Persona result = gson.fromJson(json, Persona.class);
		if(result.getFirstName() != null) {
			return repository.save(result);	
		}
		return new Persona();
	}

	@Override
	public void delete(Integer id){
		repository.deleteById(id);
	}
	
	@Override
	public Optional<Persona> findById(Integer id) {
		return repository.findById(id);
	}
	
}
