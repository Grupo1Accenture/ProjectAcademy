package com.accenture.academico.model.servicies;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.accenture.academico.model.entities.Agencia;
import com.accenture.academico.model.repositories.AgenciaRepository;
import com.accenture.academico.model.servicies.exceptions.DatabaseException;
import com.accenture.academico.model.servicies.exceptions.ResourceNotFoundException;

@Service
public class AgenciaService {

	@Autowired
	private AgenciaRepository repository; 
	
	public List<Agencia>findAll(){
		return repository.findAll();
	}
	public Agencia findById(Long id) {
		Optional<Agencia> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	public Agencia insert(Agencia obj) {
		return repository.save(obj);
	}
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	public Agencia update(Long id,Agencia obj ) {
		try {
			Agencia entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(Agencia entity, Agencia obj) {
		entity.setNome(obj.getNome());
		entity.setEndereco(obj.getEndereco());
		entity.setTelefone(obj.getTelefone());
		//entity.setClient(obj.getClient());
		
	}
}
