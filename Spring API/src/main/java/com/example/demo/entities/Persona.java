package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Persona {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String address;

	public Persona(String firstName, String lastName, String email, String address){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}

	public Persona(){

	}
	
	@Override
	public String toString() {
		return id + "" + lastName + ", " + firstName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
