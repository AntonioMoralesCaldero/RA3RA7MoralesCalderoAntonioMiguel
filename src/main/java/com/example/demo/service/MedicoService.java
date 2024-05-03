package com.example.demo.service;

import com.example.demo.entity.Medico;

import java.util.List;

public interface MedicoService {
    List<Medico> findAll();
    Medico save(Medico medico);
    Medico findById(Integer id);
    void delete(Medico medico);
}
