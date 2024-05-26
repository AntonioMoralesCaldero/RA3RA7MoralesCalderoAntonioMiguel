//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Compra;
import com.example.demo.entity.CompraMedicamento;
import com.example.demo.entity.Medicamento;
import com.example.demo.repository.CompraRepository;
import com.example.demo.repository.MedicamentoRepository;
import com.example.demo.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

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

    @Override
    public Optional<Compra> findById(int id) {
        return compraRepository.findById(id);
    }

    @Override
    public void marcarComoDispensada(int id) {
        Optional<Compra> compraOptional = findById(id);
        if (compraOptional.isPresent()) {
            Compra compra = compraOptional.get();
            compra.setDispensada(true);
            save(compra);

            for (CompraMedicamento compraMedicamento : compra.getCompraMedicamentos()) {
                Medicamento medicamento = compraMedicamento.getMedicamento();
                medicamento.setStock(medicamento.getStock() - 1);
                medicamentoRepository.save(medicamento);
            }
        }
    }
}
