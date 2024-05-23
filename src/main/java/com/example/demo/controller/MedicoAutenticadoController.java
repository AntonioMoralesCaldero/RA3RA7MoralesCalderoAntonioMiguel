//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CitaService;
import com.example.demo.service.MedicoService;
import com.example.demo.entity.Cita;
import com.example.demo.entity.Medico;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class MedicoAutenticadoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private CitaService citaService;

    @GetMapping("/perfilDeMedicos")
    public String medicoDashboard(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Medico medico = medicoService.findByUsername(username);
        Date today = date != null ? date : new Date(); // Usa la fecha proporcionada o la fecha actual si no se proporciona ninguna
        Date startOfDay = getStartOfDay(today);
        Date endOfDay = getEndOfDay(today);

        model.addAttribute("medico", medico);
        model.addAttribute("citas", citaService.findCitasForMedicoOnDate(medico.getId(), startOfDay, endOfDay));

        return "medicoindex";
    }

    private Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    @GetMapping("/perfilDeMedicos/filtrar")
    public String filtrarCitasPorFecha(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Medico medico = medicoService.findByUsername(username);


        Date startOfDay = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endOfDay = Date.from(fecha.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        List<Cita> citas = citaService.findCitasByMedicoAndDateRange(medico.getId(), startOfDay, endOfDay);
        model.addAttribute("medico", medico);
        model.addAttribute("citas", citas);

        return "medicoindex";
    }

    @GetMapping("/perfilDeMedicos/editarCita")
    public String editarCita(@RequestParam("id") int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Medico medico = medicoService.findByUsername(username);
        
        Cita cita = citaService.findById(id);
        model.addAttribute("medico", medico);
        model.addAttribute("cita", cita);
        
        return "editarCita";
    }


    @PostMapping("/perfilDeMedicos/actualizarCita")
    public String actualizarCita(@RequestParam("id") int id, 
                                 @RequestParam("observaciones") String observaciones, 
                                 @RequestParam("tratamiento") String tratamiento) {
        Cita cita = citaService.findById(id);
        cita.setObservaciones(observaciones);
        cita.setTratamiento(tratamiento);
        citaService.updateCita(cita);
        return "redirect:/perfilDeMedicos";
    }
}
