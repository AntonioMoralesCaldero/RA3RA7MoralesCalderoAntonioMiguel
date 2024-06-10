//Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Paciente findByUsername(String username);
    
    @Query("SELECT p FROM Paciente p JOIN p.compras c GROUP BY p.id ORDER BY SUM(c.precio) DESC")
    List<Paciente> findAllOrderByGastoDesc();

    @Query("SELECT p FROM Paciente p JOIN p.citas c WHERE c.medico.especialidad.id = :especialidadId GROUP BY p ORDER BY COUNT(c.id) DESC")
    List<Paciente> findAllOrderByCitasEspecialidad(int especialidadId);
}

