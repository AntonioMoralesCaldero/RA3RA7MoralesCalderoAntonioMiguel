//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            model.addAttribute("especialidadSeleccionada", especialidadId);
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

            if (!citaService.isSlotAvailable(idMedico, fecha)) {
                return "redirect:/citas/fail?reason=hora_ocupada";
            }
            if (!citaService.canBookForDay(idMedico, fecha)) {
                return "redirect:/citas/fail?reason=max_citas";
            }

            Cita cita = new Cita();
            cita.setPaciente(paciente);
            cita.setMedico(medico);
            cita.setFecha(fecha);
            citaService.save(cita);
            return "redirect:/perfil";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    
    @GetMapping("/citas/fail")
    public String showBookingFailPage(@RequestParam(required = false) String reason, Model model) {
        if (reason != null) {
            if ("hora_ocupada".equals(reason)) {
                model.addAttribute("message", "No se pudo reservar la cita porque el médico ya tiene una cita en esa hora. Por favor, intenta con otra hora.");
            } else if ("max_citas".equals(reason)) {
                model.addAttribute("message", "No se pudo reservar la cita porque el médico ya ha alcanzado el número máximo de citas para este día.");
            } else {
                model.addAttribute("message", "No se pudo procesar su solicitud. Por favor, intenta de nuevo.");
            }
        } else {
            model.addAttribute("message", "No se proporcionó información específica del error.");
        }
        return "citasFail";
    }
    
    @GetMapping("/citas/historico")
    public String mostrarHistorialCitas(Principal principal, Model model) {
        String username = principal.getName();
        Paciente paciente = pacienteService.findByUsername(username);
        if (paciente != null) {
            List<Cita> citas = citaService.findAllCitasByPacienteId(paciente.getId());
            model.addAttribute("citas", citas);
        }
        return "historicoCitas";
    }
    
    @GetMapping("/citas/futuras")
    public String mostrarCitasFuturas(Model model, Principal principal) {
        String username = principal.getName();
        List<Cita> citasFuturas = citaService.findFutureCitasByUsername(username);
        model.addAttribute("citas", citasFuturas);
        return "citasFuturas";
    }

    @GetMapping("/citas/modificar/{id}")
    public String modificarCitaForm(@PathVariable("id") int citaId, Model model) {
        Cita cita = citaService.findById(citaId);
        if (cita == null) {
            model.addAttribute("error", "No se encontró la cita con ID: " + citaId);
            return "citasFail"; 
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String fechaFormateada = dateFormat.format(cita.getFecha());
        model.addAttribute("cita", cita);
        model.addAttribute("fechaFormateada", fechaFormateada);
        return "modificarCita";
    }




    @PostMapping("/citas/modificar/{id}")
    public String actualizarCita(@PathVariable("id") int citaId, @RequestParam("fecha") String fechaStr, Principal principal, Model model) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date fecha = dateFormat.parse(fechaStr);
            Cita cita = citaService.findById(citaId);
            if (cita == null) {
                model.addAttribute("errorMessage", "No se encontró la cita con ID: " + citaId);
                return "modificarCita";
            }
            cita.setFecha(fecha);
            citaService.save(cita);
            return "redirect:/citas/futuras";
        } catch (ParseException e) {
            model.addAttribute("errorMessage", "Formato de fecha incorrecto. Por favor, sigue el formato YYYY-MM-DD HH:MM.");
            return "modificarCita";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al actualizar la cita: " + e.getMessage());
            return "modificarCita";
        }
    }



}
