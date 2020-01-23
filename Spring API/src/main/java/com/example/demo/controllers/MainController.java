package com.example.demo.controllers;

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
	
	@PostMapping(path="/", consumes = "application/json")
	public ResponseEntity<Persona> addNewPersona(@RequestBody String persona) {
		return ResponseEntity.ok(personaService.add(persona));	
	}

	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deletePersona(@PathVariable Integer id){
		if(!personaService.findById(id).isPresent()) {
			ResponseEntity.badRequest().build();
		}
		personaService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/")
	public ResponseEntity<Iterable<Persona>> getAllPersonas(){
		return ResponseEntity.ok(personaService.listar());
	}
	
	@RequestMapping("/user")
	public ResponseEntity<?> validateLogin() {
		return ResponseEntity.ok().build();
	}
	
	public boolean roleExist(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
	    Authentication authentication = context.getAuthentication();
	    for (GrantedAuthority auth : authentication.getAuthorities()) {
	    	System.out.println(auth.getAuthority().toString());
	        if (role.equals(auth.getAuthority()))
	            return true;
	    }
	    return false;
	}
}
