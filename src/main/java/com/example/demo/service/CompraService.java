// Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Compra;
import java.util.List;

public interface CompraService {
    Compra save(Compra compra);
    List<Compra> findAll();
    List<Compra> findByPacienteId(int pacienteId);

}
