package com.accenture.academico.model.servicies;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.accenture.academico.model.entities.CurrentAccount;
import com.accenture.academico.model.entities.Extract;
import com.accenture.academico.model.entities.enums.OperationStatus;
import com.accenture.academico.model.repositories.CurrentAccountRepository;
import com.accenture.academico.model.repositories.ExtractRepository;
import com.accenture.academico.model.servicies.exceptions.DatabaseException;
import com.accenture.academico.model.servicies.exceptions.ResourceNotFoundException;

@Service
public class CurrentAccountService {
	@Autowired
	private CurrentAccountRepository repository; 
	@Autowired
	private ExtractRepository repositoryExt;
	
	public List<CurrentAccount>findAll(){
		return repository.findAll();
	}
	public CurrentAccount findById(Long id) {
		Optional<CurrentAccount> obj = repository.findById(id);
		return obj.get();
	}
	public CurrentAccount insert(CurrentAccount obj) {
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
	public CurrentAccount update(Long id,CurrentAccount obj ) {
		try {
			CurrentAccount entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(CurrentAccount entity, CurrentAccount obj) {
		entity.setCurrentAccountNumber(obj.getCurrentAccountNumber());
		entity.setBalance(obj.getBalance());
	}
	public CurrentAccount withdraw(Long id, Double withdraw) {
		CurrentAccount entity = repository.getOne(id);
		withdrawData(entity, withdraw);
		return repository.save(entity);
	}
	private void withdrawData(CurrentAccount entity, Double withdraw) {
		if(entity.getBalance() >= withdraw) {
			entity.setWithdraw(withdraw);
			Extract ext = new Extract(null, OperationStatus.WITHDRAW, withdraw, entity);
			repositoryExt.save(ext);
		}
	}
	public CurrentAccount deposit(Long id, Double deposit) {
		CurrentAccount entity = repository.getOne(id);
		depositData(entity, deposit);
		return repository.save(entity);
	}
	private void depositData(CurrentAccount entity, Double deposit) {
		entity.setDeposit(deposit);
		Extract ext = new Extract(null, OperationStatus.DEPOSIT, deposit, entity);
		repositoryExt.save(ext);
	}
	public CurrentAccount transfer(Long id, Long idDestiny, Double valueDestiny) {
		CurrentAccount entity1 = repository.getOne(id);
		CurrentAccount entity2 = repository.getOne(idDestiny);
		transferData(entity1, valueDestiny, entity2, idDestiny);
		return repository.save(entity1);
	}
	private void transferData(CurrentAccount entity1, Double trans, CurrentAccount entity2, Long id) {
		try {
			if(entity2.getCurrentAccountNumber() != null) {
				entity1.setOriginTransfer(trans);
				Extract ext1 = new Extract(null, OperationStatus.ORIGIN_TRANSFER, trans, entity1);
				entity2.setDestinationTransfer(trans);
				Extract ext2 = new Extract(null, OperationStatus.DESTINATION_TRANSFER, trans, entity2);
				repositoryExt.save(ext1);
				repositoryExt.save(ext2);
			} 
		}catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
		}
	}
	public CurrentAccount recalculateBalance(Long id) {
		CurrentAccount entity = repository.getOne(id);
		recalculateBalanceData(entity);
		return repository.save(entity);
	}
	public void recalculateBalanceData(CurrentAccount entity) {
		List<Extract> extract = entity.getExtract();
		entity.setBalance(0.0);
		for(Extract item : extract) {
			switch (item.getOperationStatus().getCode()) {
			case 1:
				entity.setWithdraw(item.getOperationValue());
				break;
			case 2:
				entity.setInitialDeposit(item.getOperationValue());
				break;
			case 3:
				entity.setDeposit(item.getOperationValue());
				break;
			case 4:
				entity.setOriginTransfer(item.getOperationValue());
				break;
			case 5:
				entity.setDestinationTransfer(item.getOperationValue());
				break;
			}
		}
	}
}
