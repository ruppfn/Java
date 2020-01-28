package com.example.demo.services;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Persona;
import com.example.demo.repositories.PersonaRepository;
import com.google.gson.Gson;

@Service
public class PersonaServiceImplementation implements PersonaService{

	@Autowired
	private PersonaRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Iterable<Persona> listar() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Persona> findByFirstName(String firstName){
		return repository.findByFirstName(firstName);
	}

	
	
	@Override
	public Persona add(String json) {
		Persona result = getPersonaFromJson(json);
		return result.isNull()? repository.save(result) : new Persona();
	}
	
	public Persona add(Persona persona) {
		return repository.save(persona);
	}

	@Override
	public void delete(Integer id){
		repository.deleteById(id);
	}
	
	@Override
	public Optional<Persona> findById(Integer id) {
		return repository.findById(id);
	}
	
	public Persona getPersonaFromJson(String json) {
		Gson gson = new Gson();
		Persona result = gson.fromJson(json, Persona.class);
		return result.isNull() ? result : new Persona();
	}
	
	public int getMaxId() {
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("OBTENER_ID");
		
		query.registerStoredProcedureParameter("max", Integer.class, ParameterMode.OUT);
		
		query.execute();
		
		return (int) query.getOutputParameterValue(1);
	}
	
}
