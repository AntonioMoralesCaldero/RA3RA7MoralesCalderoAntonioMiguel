//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;


import com.example.demo.entity.Cita;
import com.example.demo.repository.CitaRepository;
import com.example.demo.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
    public int contarCitasPorFechaHora(int idMedico, Date fechaHora) {
        return citaRepository.countByMedicoIdAndFecha(idMedico, fechaHora);
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
    
    @Override
    public boolean isSlotAvailable(int medicoId, Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MINUTE, -30);
        Date startTime = cal.getTime();
        cal.add(Calendar.MINUTE, 60);
        Date endTime = cal.getTime();
        return citaRepository.countCitasByTimeSlot(medicoId, startTime, endTime) == 0;
    }

    @Override
    public boolean canBookForDay(int medicoId, Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date startOfDay = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date endOfDay = cal.getTime();
        return citaRepository.countCitasByDay(medicoId, startOfDay, endOfDay) < 3;
    }
    
    public List<Cita> findAllCitasByPacienteId(int pacienteId) {
        Date now = new Date();
        return citaRepository.findPastCitasByPacienteId(pacienteId, now);
    }
    
    @Override
    public List<Cita> findFutureCitasByUsername(String username) {
        return citaRepository.findFutureCitasByUsername(username, new Date());
    }

    @Override
    public boolean canModifyCita(int citaId, Date nuevaFecha) {
        Cita cita = citaRepository.findById(citaId).orElse(null);
        if (cita != null) {
            Date currentDate = new Date();
            long difference = cita.getFecha().getTime() - currentDate.getTime();
            return difference > 24 * 60 * 60 * 1000;
        }
        return false;
    }

    public void updateCitaFecha(int citaId, Date nuevaFecha) {
        Cita cita = citaRepository.findById(citaId).orElse(null);
        if (cita != null) {
            cita.setFecha(nuevaFecha);
            citaRepository.save(cita);
        } else {
            throw new IllegalArgumentException("Cita no encontrada con ID: " + citaId);
        }
    }
    
    @Override
    public List<Cita> findCitasByMedicoAndDate(int medicoId, Date date) {
        return citaRepository.findByMedicoIdAndFecha(medicoId, date);
    }
    
    @Override
    public List<Cita> findCitasForMedicoOnDate(int medicoId, Date startOfDay, Date endOfDay) {
        return citaRepository.findCitasForMedicoOnDate(medicoId, startOfDay, endOfDay);
    }
    
    @Override
    public List<Cita> findCitasByMedicoAndDateRange(int medicoId, Date startOfDay, Date endOfDay) {
        return citaRepository.findByMedicoIdAndDateRange(medicoId, startOfDay, endOfDay);
    }

}

