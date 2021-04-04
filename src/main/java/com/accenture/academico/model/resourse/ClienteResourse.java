package com.accenture.academico.model.resourse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academico.model.entities.Cliente;

@RestController
@RequestMapping(value= "/clientes")
public class ClienteResourse {
	@GetMapping
	public ResponseEntity<Cliente> findAll(){
		Cliente cliente = new Cliente(1L, "Diogo","999.999.999-99" , "81999999999");
		//User u = new User(1, "", "", "", "");
		return ResponseEntity.ok().body(cliente);
	}
}
