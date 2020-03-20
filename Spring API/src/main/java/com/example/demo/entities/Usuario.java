package com.example.demo.entities;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuarios")
public class Usuario{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable=false)
	private int id;
	
	@NotEmpty
	@Column(nullable = false)
	private String username;
	
	@NotEmpty
	@Column(nullable = false)
	private String password;

	private String token;
	
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name= "role_id"))
	private Set<Role> roles;
	
	public Usuario() {
    }

    public Usuario(Usuario users) {
        this.token = users.getToken();
        this.roles = users.getRoles();
        this.username = users.getUsername();
        this.id = users.getId();
        this.password = users.getPassword();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
