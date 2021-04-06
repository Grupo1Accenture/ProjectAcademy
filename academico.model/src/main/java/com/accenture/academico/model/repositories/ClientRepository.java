package com.accenture.academico.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academico.model.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
