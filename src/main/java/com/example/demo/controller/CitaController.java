//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Cita;
import com.example.demo.entity.Especialidad;
import com.example.demo.entity.Medico;
import com.example.demo.entity.Paciente;
import com.example.demo.service.CitaService;
import com.example.demo.service.EspecialidadService;
import com.example.demo.service.MedicoService;
import com.example.demo.service.PacienteService;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CitaController {
	
    @Autowired
    private EspecialidadService especialidadService;
    
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private CitaService citaService;

    @GetMapping("/citas/nueva")
    public String mostrarFormularioDeCitas(@RequestParam(required = false) Integer especialidadId, Model model) {
        List<Especialidad> especialidades = especialidadService.findAll();
        model.addAttribute("especialidades", especialidades);

        if (especialidadId != null) {
            List<Medico> medicos = medicoService.findByEspecialidad(especialidadId);
            model.addAttribute("medicos", medicos);
            model.addAttribute("especialidadSeleccionada", especialidadId);  // Asegúrate de que este atributo se pasa correctamente
        } else {
            model.addAttribute("medicos", List.of());
        }

        return "reservarCita";
    }

    @PostMapping("/citas/nueva")
    public String reservarCita(@RequestParam("idMedico") int idMedico,
                               @RequestParam("fecha") String fechaStr,
                               @RequestParam("hora") String horaStr,
                               Principal principal) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date fecha = dateFormat.parse(fechaStr + " " + horaStr);

            String username = principal.getName();
            Paciente paciente = pacienteService.findByUsername(username);
            Medico medico = medicoService.findById(idMedico);

            if (citaService.contarCitasPorFechaHora(idMedico, fecha) < 3) {
                Cita cita = new Cita();
                cita.setPaciente(paciente);
                cita.setMedico(medico);
                cita.setFecha(fecha);
                citaService.save(cita);
                return "redirect:/perfil";
            } else {
                return "citasFail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
