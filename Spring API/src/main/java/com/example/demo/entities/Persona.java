package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable=false)
	private Integer id;

	@Column(name="FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name="LAST_NAME", nullable = false)
	private String lastName;

	@Column
	private String email;

	@Column
	private String address;
	
	public Persona(String firstName, String lastName, String email, String address){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}

	public Persona(){

	}
	
	public boolean isNull() {
		if(this.firstName == null || this.lastName == null || this.email == null || this.address == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "{ 'firstName': " + firstName + ", 'lastName': " + lastName + ", 'email': " + email + 
				", 'address': " + address + "}";
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
