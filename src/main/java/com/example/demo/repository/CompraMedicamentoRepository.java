//Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.CompraMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraMedicamentoRepository extends JpaRepository<CompraMedicamento, Integer> {
}
