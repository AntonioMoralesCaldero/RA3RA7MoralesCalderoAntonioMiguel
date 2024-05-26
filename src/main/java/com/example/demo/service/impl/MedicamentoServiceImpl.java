//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Medicamento;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> findAll() {
        return medicamentoRepository.findAll();
    }

    @Override
    public Medicamento findById(int id) {
        return medicamentoRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Medicamento medicamento) {
        medicamentoRepository.save(medicamento);
    }

    @Override
    public void delete(int id) {
        medicamentoRepository.deleteById(id);
    }
    
    @Override
    public List<Medicamento> findByNombreContaining(String nombre) {
        return medicamentoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Override
    public List<Medicamento> findAllOrderByStockAsc() {
        return medicamentoRepository.findAllByOrderByStockAsc();
    }
}
