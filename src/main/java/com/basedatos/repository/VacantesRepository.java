package com.basedatos.repository;


import com.basedatos.model.Vacante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VacantesRepository extends JpaRepository<Vacante,Integer>{
    
}
