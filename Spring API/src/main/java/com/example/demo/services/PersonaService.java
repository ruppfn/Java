package com.example.demo.services;

import com.example.demo.entities.Persona;

public interface PersonaService {
	Iterable<Persona> listar();
	Persona add(String json);
	void delete(Integer id);
}
