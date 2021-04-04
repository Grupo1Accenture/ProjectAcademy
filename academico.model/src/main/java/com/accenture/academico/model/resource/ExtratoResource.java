package com.accenture.academico.model.resource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academico.model.entities.ContaCorrente;
import com.accenture.academico.model.entities.Extrato;
import com.accenture.academico.model.servicies.ExtratoService;

@RestController
@RequestMapping(value= "/extratos")
public class ExtratoResource {
	@Autowired
	private ExtratoService service;
	
	@GetMapping
	public ResponseEntity<List<Extrato>> findAll(){
		List<Extrato> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Extrato> findById(@PathVariable Long id){
		Extrato obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping(value = "/consulta/{id}")
	public ResponseEntity<List<Extrato>> extratoById(@PathVariable Long id){
		ContaCorrente obj = service.extratoById(id);
		return ResponseEntity.ok().body(obj.getExtrato());
	}
	/*@PostMapping
	public ResponseEntity<Extrato> insert (@RequestBody Extrato obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	/*@PutMapping(value = "/{id}")
	public ResponseEntity<Extrato> update(@PathVariable Long id, @RequestBody Extrato obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}*/
}
