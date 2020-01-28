/*package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

import java.util.Iterator;

import com.example.demo.controllers.MainController;
import com.example.demo.entities.Persona;
import com.example.demo.services.PersonaServiceImplementation;

@RunWith(SpringRunner.class)
@WebMvcTest
class DemoApplicationTests {
	
	@MockBean
	MainController controller;
	
	@Autowired
	PersonaServiceImplementation personaService;
	
	@Autowired
	private MockMvc mvc;
	
	public String personaString = "{ 'firstName': 'NombreTest', 'lastName': 'ApellidoTest', 'email': 'email@test.com', 'address': 'Calle Test 123'}";
	public Persona personaVar = new Persona("NombreTest", "ApellidoTest", "email@test.com", "Calle Test 123");
	
	@BeforeEach
	public void inicializar() {
		
	}
	
	@Test
	public void agregarUnaPersona() {
		assertEquals(personaVar.toString(),controller.addNewPersona(personaString).getBody().toString());
		
	}
	
	@Test
	public void borrarUnaPersona() {
		personaVar.setId(15150);
		personaService.add(personaVar);
		boolean wasSaved = personaService.findById(personaVar.getId()).isPresent();
		personaService.delete(personaVar.getId());
		boolean isDeleted = !personaService.findById(personaVar.getId()).isPresent();
		assert(wasSaved && isDeleted);
	}
	
	@Test
	public void obtenerTodasLasPersonas() {
		Iterable<Persona> personas = personaService.listar();
		given(arrivalController.getAllPersonas())
		
//		Iterator<Persona> serviceIterator = personaService.listar().iterator();
//		Iterator<Persona> controllerIterator = controller.getAllPersonas().getBody().iterator();
//		
//		while(serviceIterator.hasNext() || controllerIterator.hasNext()) {
//			assertEquals(serviceIterator.next().toString(), controllerIterator.next().toString());
		}
	}
	

}
*/