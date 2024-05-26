//Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.Compra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
    List<Compra> findByPacienteId(int pacienteId);

}
