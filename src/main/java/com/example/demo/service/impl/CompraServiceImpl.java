// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Compra;
import com.example.demo.repository.CompraRepository;
import com.example.demo.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public List<Compra> findAll() {
        return compraRepository.findAll();
    }
    
    @Override
    public List<Compra> findByPacienteId(int pacienteId) {
        return compraRepository.findByPacienteId(pacienteId);
    }
}
