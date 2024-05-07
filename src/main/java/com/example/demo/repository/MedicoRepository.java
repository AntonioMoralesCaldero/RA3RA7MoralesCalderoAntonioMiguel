//Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Medico findByUsername(String username);
    List<Medico> findByEspecialidadId(int especialidadId);
}