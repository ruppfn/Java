package com.example.demo.repositories;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Persona;
import com.example.demo.services.PersonaServiceImplementation;
import com.example.demo.services.UserDetailsServiceImplementation;

public class UseExistingIdOtherwiseGenerateUsingIdentity extends IdentityGenerator {

	@Autowired
	PersonaServiceImplementation personaService;
	
	@Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Persona persona = (Persona) object;
    	Serializable id = persona.getId();
        if(id != null) {
        	return id;
        } else {
        	return personaService.getMaxId()+1;
        }
    }
}
