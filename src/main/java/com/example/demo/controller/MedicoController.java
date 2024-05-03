package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Especialidad;
import com.example.demo.entity.Medico;
import com.example.demo.service.EspecialidadService;
import com.example.demo.service.MedicoService;

@Controller
@RequestMapping("/medicoDashboard")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoService.findAll());
        return "listaMedicos";
    }

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoMedico(Model model) {
        Medico medico = new Medico();
        medico.setEspecialidad(new Especialidad()); // Inicializa Especialidad aquí si es necesario
        model.addAttribute("medico", medico);
        model.addAttribute("especialidades", especialidadService.findAll()); // Asume que tienes un servicio que retorna todas las especialidades
        return "formularioMedico";
    }


    @PostMapping("/guardar")
    public String guardarMedico(@ModelAttribute("medico") Medico medico, BindingResult result) {
        if (result.hasErrors()) {
            return "formularioMedico";
        }

        if (medico.getId() != null && medico.getId() > 0) {
            // Es una edición
            Medico existingMedico = medicoService.findById(medico.getId());
            if (existingMedico != null) {
                existingMedico.setNombre(medico.getNombre());
                existingMedico.setApellidos(medico.getApellidos());
                existingMedico.setEdad(medico.getEdad());
                existingMedico.setFechaalta(medico.getFechaalta());
                existingMedico.setEspecialidad(medico.getEspecialidad());
                medicoService.save(existingMedico);
            }
        } else {
            medico.setPassword(passwordEncoder.encode(medico.getPassword()));
            medicoService.save(medico);
        }
        return "redirect:/medicoDashboard/";
    }



    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Integer id, Model model) {
        Medico medico = medicoService.findById(id);
        if (medico == null) {
            return "redirect:/medicoDashboard/";
        }
        model.addAttribute("medico", medico);
        model.addAttribute("especialidades", especialidadService.findAll());
        return "formularioMedico";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarMedico(@PathVariable Integer id) {
        Medico medico = medicoService.findById(id);
        if (medico != null) {
            medicoService.delete(medico);
        }
        return "redirect:/medicoDashboard/";
    }


}
