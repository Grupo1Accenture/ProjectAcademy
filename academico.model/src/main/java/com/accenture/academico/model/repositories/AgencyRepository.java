package com.accenture.academico.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academico.model.entities.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Long>{

}
