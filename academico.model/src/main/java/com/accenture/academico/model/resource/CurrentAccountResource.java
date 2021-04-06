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

import com.accenture.academico.model.entities.CurrentAccount;
import com.accenture.academico.model.servicies.CurrentAccountService;

@RestController
@RequestMapping(value= "/current_account")
public class CurrentAccountResource {
	@Autowired
	private CurrentAccountService service;
	
	@GetMapping
	public ResponseEntity<List<CurrentAccount>> findAll(){
		List<CurrentAccount> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<CurrentAccount> findById(@PathVariable Long id){
		CurrentAccount obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping
	public ResponseEntity<CurrentAccount> insert(@RequestBody CurrentAccount obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<CurrentAccount> update(@PathVariable Long id, @RequestBody CurrentAccount obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	@PutMapping(value = "/withdraw/{id}")
	public ResponseEntity<CurrentAccount> withdraw(@PathVariable Long id, @RequestParam Double withdraw){
		CurrentAccount obj = service.withdraw(id, withdraw);
		return ResponseEntity.ok().body(obj);
	}
	@PutMapping(value = "/deposit/{id}")
	public ResponseEntity<CurrentAccount> deposit(@PathVariable Long id, @RequestParam Double deposit){
		CurrentAccount obj = service.deposit(id, deposit);
		return ResponseEntity.ok().body(obj);
	}
	@PutMapping(value = "/transfer/{id}")
	public ResponseEntity<CurrentAccount> transfer(@PathVariable Long id, @RequestParam Long idDestiny, Double valueDestiny){
		CurrentAccount obj = service.transfer(id, idDestiny, valueDestiny);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping(value = "/recalculate_balance/{id}")
	public ResponseEntity<CurrentAccount> recalculateBalance(@PathVariable Long id){
		CurrentAccount obj = service.recalculateBalance(id);
		return ResponseEntity.ok().body(obj);
	}
}
