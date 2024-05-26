// Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.Medicamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);
    List<Medicamento> findAllByOrderByStockAsc();

}
