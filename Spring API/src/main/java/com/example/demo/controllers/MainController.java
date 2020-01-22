package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entities.Persona;
import com.example.demo.services.PersonaServiceImplementation;

@Controller
@RequestMapping(value="/persona", produces="application/json")
public class MainController {
	
	@Autowired
	private PersonaServiceImplementation personaService;
	
	@PostMapping(path="/", consumes = "application/json")
	public ResponseEntity<Persona> addNewPersona(@RequestBody String persona) {
		return ResponseEntity.ok(personaService.add(persona));	
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deletePersona(@PathVariable Integer id){
		if(!personaService.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}
		personaService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@GetMapping("/")
	public ResponseEntity<Iterable<Persona>> getAllPersonas(){
		return ResponseEntity.ok(personaService.listar());
	}
	
	@RequestMapping("/user")
	public ResponseEntity<?> validateLogin() {
		return ResponseEntity.ok().build();
	}
}
