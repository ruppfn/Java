package com.example.demo.controllers;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Role;
import com.example.demo.entities.Usuario;
import com.example.demo.services.UserDetailsServiceImplementation;

@RestController
@RequestMapping(value="/", consumes="application/json")
public class UserController {

	@Autowired
	private UserDetailsServiceImplementation userService;
	
	@PostMapping
	public ResponseEntity<?> addNewUser(@RequestBody Usuario user){
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setRoleId(2);
		role.setRole("USER");
		roles.add(role);
		
		int id = userService.getMaxId();
		user.setId(id);
		user.setActive(1);
		user.setRoles(roles);
		
		userService.createNewUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(user.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
