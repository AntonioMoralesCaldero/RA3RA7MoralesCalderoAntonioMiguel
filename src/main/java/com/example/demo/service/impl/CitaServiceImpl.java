//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;


import com.example.demo.entity.Cita;
import com.example.demo.repository.CitaRepository;
import com.example.demo.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Cita save(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public int contarCitasPorFecha(int idMedico, Date fecha) {
        return citaRepository.countByMedicoIdAndFecha(idMedico, fecha);
    }

    @Override
    public List<Cita> findByMedicoAndDate(int idMedico, Date fecha) {
        return citaRepository.findByMedicoIdAndFecha(idMedico, fecha);
    }

    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        citaRepository.deleteById(id);
    }

    @Override
    public Cita findById(int id) {
        return citaRepository.findById(id).orElse(null);
    }
}

