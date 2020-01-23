package com.example.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Persona;

@Service
public interface PersonaService {
	Iterable<Persona> listar();
	Persona add(String json);
	void delete(Integer id);
	Optional<Persona> findById(Integer id);
}
