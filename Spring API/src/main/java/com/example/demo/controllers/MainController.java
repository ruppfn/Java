package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Persona;
import com.example.demo.services.PersonaServiceImplementation;

@Controller
@RequestMapping(path="/persona")
public class MainController {
	
	@Autowired
	private PersonaServiceImplementation personaService;
	
	@PostMapping(
			path="/add",
			consumes = "application/json",
			produces = "application/json")
	public @ResponseBody Persona addNewPersona(@RequestParam String persona) {
		System.out.println("Add: " + persona.toString());
		return personaService.add(persona);	
	}

	@DeleteMapping(path="/delete")
	public @ResponseBody void deletePersona(@RequestParam Integer id){
		System.out.println("ID: " + id);
		personaService.delete(id);
	}
	
	@GetMapping(path="/view")
	public @ResponseBody Iterable<Persona> getAllPersonas(){
		System.out.println("Listado");
		return personaService.listar();
	}
}
