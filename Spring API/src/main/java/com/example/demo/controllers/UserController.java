package com.example.demo.controllers;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Role;
import com.example.demo.entities.Usuario;
import com.example.demo.services.UserDetailsServiceImplementation;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserDetailsServiceImplementation userService;
	
	@PostMapping
	public ResponseEntity<Usuario> login(@RequestHeader("user") String username, @RequestHeader("password") String pwd){
		Optional<Usuario> usuario = userService.findByUsername(username);
		Usuario user;
		if(usuario.isPresent()) {
			user = usuario.get();
			if(user.getPassword().equals(pwd)) {
				String token = userService.getJWTToken(user);
				user.setToken(token);
				return new ResponseEntity<Usuario>(user, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> addNewUser(@RequestHeader("user") String username, @RequestHeader String password){
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setRoleId(2);
		role.setRole("USER");
		roles.add(role);

		Usuario user = new Usuario();
		user.setUsername(username);
		user.setPassword(password);
		user.setRoles(roles);
		
		userService.createNewUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(user.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
