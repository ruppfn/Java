package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.services.UsuarioServiceImplementation;

@Entity
public class Usuario implements UserDetails{

	@Id
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	private String authority;
	
	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
	@Autowired
	private UsuarioServiceImplementation userService;
	
	public Usuario() {
		
	}
	
	public Usuario(String username, String password) {
		this.username = username;
		this.password = 
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
