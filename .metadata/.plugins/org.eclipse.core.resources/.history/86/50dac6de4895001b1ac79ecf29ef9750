package com.accenture.academico.model.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.academico.model.entities.ContaCorrente;
import com.accenture.academico.model.entities.Extrato;
import com.accenture.academico.model.repositories.ContaCorrenteRepository;
import com.accenture.academico.model.repositories.ExtratoRepository;
import com.accenture.academico.model.servicies.exceptions.ResourceNotFoundException;

@Service
public class ExtratoService {

	@Autowired
	private ExtratoRepository repository; 
	@Autowired
	private ContaCorrenteRepository repositorycc;
	
	public List<Extrato>findAll(){
		return repository.findAll();
	}
	public Extrato findById(Long id) {
		Optional<Extrato> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	public ContaCorrente extratoById(Long id) {
		Optional<ContaCorrente> obj = repositorycc.findById(id);
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
