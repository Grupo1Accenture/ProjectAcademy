package com.accenture.academico.model.servicies;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.accenture.academico.model.entities.ContaCorrente;
import com.accenture.academico.model.entities.Extrato;
import com.accenture.academico.model.repositories.ContaCorrenteRepository;
import com.accenture.academico.model.servicies.exceptions.DatabaseException;
import com.accenture.academico.model.servicies.exceptions.ResourceNotFoundException;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaCorrenteRepository repository; 
	
	public List<ContaCorrente>findAll(){
		return repository.findAll();
	}
	public ContaCorrente findById(Long id) {
		Optional<ContaCorrente> obj = repository.findById(id);
		return obj.get();
	}
	public ContaCorrente insert(ContaCorrente obj) {
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
	public ContaCorrente update(Long id,ContaCorrente obj ) {
		try {
			ContaCorrente entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(ContaCorrente entity, ContaCorrente obj) {
		entity.setContaCorrenteNumero(obj.getContaCorrenteNumero());
		entity.setSaldo(obj.getSaldo());
		
	}
	public ContaCorrente saque(Long id, Double saque) {
		ContaCorrente entity = repository.getOne(id);
		saqueData(entity, saque);
		return repository.save(entity);
	}
	private void saqueData(ContaCorrente entity, Double saque) {
		if(entity.getSaldo() >= saque) {
			entity.setSaque(saque);
		}
	}
	public ContaCorrente deposito(Long id, Double deposito) {
		ContaCorrente entity = repository.getOne(id);
		depositoData(entity, deposito);
		return repository.save(entity);
	}
	private void depositoData(ContaCorrente entity, Double deposito) {
		entity.setDeposito(deposito);
		
	}
	public ContaCorrente transferencia(Long id, Long idDestino, Double valorDestino) {
		ContaCorrente entity1 = repository.getOne(id);
		ContaCorrente entity2 = repository.getOne(idDestino);
		transferenciaData(entity1, valorDestino, entity2, idDestino);
		return repository.save(entity1);
	}
	private void transferenciaData(ContaCorrente entity1, Double trans, ContaCorrente entity2, Long id) {
		try {
			if(entity2.getContaCorrenteNumero() != null) {
				entity1.setSaque(trans);
				entity2.setDeposito(trans);
			} 
		}catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			
		}
		
		
	}
	public ContaCorrente recalcularSaldo(Long id) {
		ContaCorrente entity = repository.getOne(id);
		recalcularSaldoData(entity);
		return repository.save(entity);
	}
	public void recalcularSaldoData(ContaCorrente entity) {
		List<Extrato> extrato = entity.getExtrato();
		for(Extrato item : extrato) {
			if(item.getOperationStatus().getCode() == 1 ) {
				entity.setSaldo(entity.getSaldo() - item.getValorOperacao());	
			}else if(item.getOperationStatus().getCode() == 2 || item.getOperationStatus().getCode() == 3) {
				entity.setSaldo(entity.getSaldo() + item.getValorOperacao());	
			}
		}
	}
}
