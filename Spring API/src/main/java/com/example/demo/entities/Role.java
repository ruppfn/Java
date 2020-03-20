package com.example.demo.entities;


import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private int roleId;

    @Column
    private String role;
    
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> users; 

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
