package com.accenture.academico.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academico.model.entities.CurrentAccount;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long>{

}
