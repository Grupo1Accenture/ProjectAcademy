package com.accenture.academico.model.resource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academico.model.entities.CurrentAccount;
import com.accenture.academico.model.entities.Extract;
import com.accenture.academico.model.servicies.ExtractService;

@RestController
@RequestMapping(value= "/extratos")
public class ExtractResource {
	@Autowired
	private ExtractService service;
	
	@GetMapping
	public ResponseEntity<List<Extract>> findAll(){
		List<Extract> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Extract> findById(@PathVariable Long id){
		Extract obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping(value = "/consulta/{id}")
	public ResponseEntity<List<Extract>> extractById(@PathVariable Long id){
		CurrentAccount obj = service.extractById(id);
		return ResponseEntity.ok().body(obj.getExtract());
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
