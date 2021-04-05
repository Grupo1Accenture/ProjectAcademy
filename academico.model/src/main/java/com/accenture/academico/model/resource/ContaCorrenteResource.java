package com.accenture.academico.model.resource;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.academico.model.entities.ContaCorrente;
import com.accenture.academico.model.servicies.ContaCorrenteService;

@RestController
@RequestMapping(value= "/contacorrente")
public class ContaCorrenteResource {
	@Autowired
	private ContaCorrenteService service;
	
	@GetMapping
	public ResponseEntity<List<ContaCorrente>> findAll(){
		List<ContaCorrente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContaCorrente> findById(@PathVariable Long id){
		ContaCorrente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping
	public ResponseEntity<ContaCorrente> insert (@RequestBody ContaCorrente obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delele(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContaCorrente> update(@PathVariable Long id, @RequestBody ContaCorrente obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	@PutMapping(value = "/saque/{id}")
	public ResponseEntity<ContaCorrente> saque(@PathVariable Long id, @RequestParam Double saque){
		ContaCorrente obj = service.saque(id, saque);
		return ResponseEntity.ok().body(obj);
	}
	@PutMapping(value = "/deposito/{id}")
	public ResponseEntity<ContaCorrente> deposito(@PathVariable Long id, @RequestParam Double deposito){
		ContaCorrente obj = service.deposito(id, deposito);
		return ResponseEntity.ok().body(obj);
	}
	@PutMapping(value = "/transferencia/{id}")
	public ResponseEntity<ContaCorrente> transferencia(@PathVariable Long id, @RequestParam Long idDestino, Double valorDestino){
		ContaCorrente obj = service.transferencia(id, idDestino, valorDestino);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping(value = "/recalcularsaldo/{id}")
	public ResponseEntity<ContaCorrente> recalcularSaldo(@PathVariable Long id){
		ContaCorrente obj = service.recalcularSaldo(id);
		return ResponseEntity.ok().body(obj);
	}
}
