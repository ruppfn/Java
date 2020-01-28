package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Persona;
import com.example.demo.services.PersonaServiceImplementation;

@RestController
@RequestMapping(value="/persona", produces="application/json")
public class MainController {
	
	@Autowired
	private PersonaServiceImplementation personaService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Persona> addNewPersona(@RequestBody String persona) {
		Persona fromJson = personaService.getPersonaFromJson(persona);
		if(!fromJson.isNull()) {
			return ResponseEntity.ok(personaService.add(persona));	
		} else {
			return ResponseEntity.badRequest().build();
		}
		
	}

	@DeleteMapping(path="{id}")
	public ResponseEntity<?> deletePersona(@PathVariable Integer id){
		if(!personaService.findById(id).isPresent()) {
			ResponseEntity.notFound().build();
		}
		
		personaService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("")
	public ResponseEntity<Iterable<Persona>> getAllPersonas(){
		return ResponseEntity.ok(personaService.listar());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Persona> getPersonaById(@PathVariable Integer id){
		Optional<Persona> optional = personaService.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
 	}
	
	@PutMapping(consumes = "application/json", path = "{id}")
	public ResponseEntity<Persona> modifyPersona(@RequestBody String persona, @PathVariable Integer id){
		Optional<Persona> optional = personaService.findById(id);
		if(optional.isPresent()) {
			Persona saved = optional.get();
			Persona fromJson = personaService.getPersonaFromJson(persona);
			
			saved.setFirstName(fromJson.getFirstName());
			saved.setLastName(fromJson.getLastName());
			saved.setAddress(fromJson.getAddress());
			saved.setEmail(fromJson.getEmail());
			
			return ResponseEntity.ok(personaService.add(saved));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
