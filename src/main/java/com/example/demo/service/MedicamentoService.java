// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Medicamento;
import java.util.List;

public interface MedicamentoService {
    List<Medicamento> findAll();
    Medicamento findById(int id);
    void save(Medicamento medicamento);
    void delete(int id);
    List<Medicamento> findByNombreContaining(String nombre);
	List<Medicamento> findAllOrderByStockAsc();
}
