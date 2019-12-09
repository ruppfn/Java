package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.entities.Persona;
import com.example.demo.entities.Usuario;
import com.example.demo.services.PersonaServiceImplementation;

@Controller
@RequestMapping("/persona")
public class MainController {
	
	@Autowired
	private PersonaServiceImplementation personaService;
	
	@PostMapping(
			path="/",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<Persona> addNewPersona(@RequestParam String persona) {
		return ResponseEntity.ok(personaService.add(persona));	
	}

	@DeleteMapping(path="/{id}")
	public ResponseEntity deletePersona(@PathVariable Integer id){
		if(!personaService.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}
		personaService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Persona>> getAllPersonas(){
		return ResponseEntity.ok(personaService.listar());
	}
	
	@GetMapping(produces = "application/json")
	@RequestMapping("/user")
	public ResponseEntity validateLogin() {
		return ResponseEntity.ok().build();
	}
}
