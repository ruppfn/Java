package com.example.demo.controllers;

import java.util.List;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Role;
import com.example.demo.entities.Usuario;
import com.example.demo.services.UserDetailsServiceImplementation;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserDetailsServiceImplementation userService;
	
	@PostMapping
	public ResponseEntity<Usuario> login(@RequestParam("user") String username, @RequestParam("password") String pwd){
		Optional<Usuario> usuario = userService.findByUsername(username);
		Usuario user;
		if(usuario.isPresent()) {
			user = usuario.get();
			if(user.getPassword().equals(pwd)) {
				String token = getJWTToken(user);
				user.setToken(token);
				return new ResponseEntity<Usuario>(user, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.UNAUTHORIZED);
	}
	
	private String getJWTToken(Usuario user){
		String secretKey = "secretKey";
		String roles = "";
		for (Role role : user.getRoles()) {
			roles = roles.length() > 1 ? role.getRole() : roles + "," + role.getRole();
		}
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		
		String token = Jwts.builder()
				.setId("TestJWT")
				.setSubject(user.getUsername())
				.claim("authorities", grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
				.compact();		
		
		return "Prefix " + token;
	}
	
	@PostMapping("new")
	public ResponseEntity<?> addNewUser(@RequestBody Usuario user){
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setRoleId(2);
		role.setRole("USER");
		roles.add(role);
		
		int id = userService.getMaxId();
		user.setId(id);
		user.setRoles(roles);
		
		userService.createNewUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(user.getId())
							.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
