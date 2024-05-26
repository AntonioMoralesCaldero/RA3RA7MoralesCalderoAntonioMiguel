//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Compra;
import java.util.List;
import java.util.Optional;

public interface CompraService {
    Compra save(Compra compra);
    List<Compra> findAll();
    List<Compra> findByPacienteId(int pacienteId);
    Optional<Compra> findById(int id);
    void marcarComoDispensada(int id);
}
