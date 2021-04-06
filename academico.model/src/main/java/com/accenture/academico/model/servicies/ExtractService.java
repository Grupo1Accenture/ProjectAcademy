package com.accenture.academico.model.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.academico.model.entities.CurrentAccount;
import com.accenture.academico.model.entities.Extract;
import com.accenture.academico.model.repositories.CurrentAccountRepository;
import com.accenture.academico.model.repositories.ExtractRepository;
import com.accenture.academico.model.servicies.exceptions.ResourceNotFoundException;

@Service
public class ExtractService {

	@Autowired
	private ExtractRepository repository; 
	@Autowired
	private CurrentAccountRepository repositorycc;
	
	public List<Extract>findAll(){
		return repository.findAll();
	}
	public Extract findById(Long id) {
		Optional<Extract> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	public CurrentAccount extractById(Long id) {
		Optional<CurrentAccount> obj = repositorycc.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	/*public Extrato findById(Long id) {
		List<Extrato> list = repository.findAll();
		List<Extrato> result = new ArrayList<>();
		int i = list.getConta().getId();
		for(integer item : list.get(4)) {
			
		}
		Extrato ext = repository.getOne(id);
		for(Extrato value : ext.getConta()) {
			if(value.getConta(id) == id) {
				
			}
		}
		return ext.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	/*public Extrato insert(Extrato obj) {
		return repository.save(obj);
	}
	/*public Extrato update(Long id,Extrato obj ) {
		try {
			Extrato entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(Extrato entity, Extrato obj) {
		entity.setDataHoraMovimento(Instant.now());
		//entity.setValorOperacao(obj.getValorOperacao());
		
		
		
	}*/
}
