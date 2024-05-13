//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Cita;

import java.util.Date;
import java.util.List;

public interface CitaService {

    Cita save(Cita cita);
    int contarCitasPorFechaHora(int idMedico, Date fechaHora);
    List<Cita> findByMedicoAndDate(int idMedico, Date fecha);
    List<Cita> findAll();
    void deleteById(int id);
    Cita findById(int id);
    boolean isSlotAvailable(int medicoId, Date fecha);
    boolean canBookForDay(int medicoId, Date fecha);
}
