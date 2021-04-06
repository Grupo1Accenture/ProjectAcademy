package com.accenture.academico.model.servicies;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.accenture.academico.model.entities.Agency;
import com.accenture.academico.model.repositories.AgencyRepository;
import com.accenture.academico.model.servicies.exceptions.DatabaseException;
import com.accenture.academico.model.servicies.exceptions.ResourceNotFoundException;

@Service
public class AgencyService {

	@Autowired
	private AgencyRepository repository; 
	
	public List<Agency>findAll(){
		return repository.findAll();
	}
	public Agency findById(Long id) {
		Optional<Agency> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	public Agency insert(Agency obj) {
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
	public Agency update(Long id,Agency obj ) {
		try {
			Agency entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(Agency entity, Agency obj) {
		entity.setName(obj.getName());
		entity.setAddress(obj.getAddress());
		entity.setPhone(obj.getPhone());
		//entity.setClient(obj.getClient());
		
	}
}
