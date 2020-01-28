package com.example.demo.services;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = userRepository.findByUsername(username);
		Usuario user;
		if(optional.isPresent()) {
			user = optional.get();
		} else {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
	}
	
	public Optional<Usuario> findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	
	
	private static Collection<? extends GrantedAuthority> getAuthorities(Usuario user){
		String[] userRoles = user.getRoles().stream().map((role) -> role.getRole() ).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
	
	public Usuario createNewUser(Usuario user) throws AuthenticationServiceException{
		Optional<Usuario> optional = userRepository.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			throw new AuthenticationServiceException("Username already exists");
		} else {
			return userRepository.save(user);
		}
	}
	
}
