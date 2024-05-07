//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Especialidad;
import com.example.demo.entity.Medico;
import com.example.demo.service.EspecialidadService;
import com.example.demo.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VisitanteController {

    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/medicosyespecialidades")
    public String listarEspecialidadesYMedicos(@RequestParam(required = false) Integer especialidadId, Model model) {
        List<Especialidad> especialidades = especialidadService.findAll();
        model.addAttribute("especialidades", especialidades);
        model.addAttribute("especialidadSeleccionada", especialidadId);
        List<Medico> medicos = (especialidadId != null) ? medicoService.findByEspecialidad(especialidadId) : List.of();
        model.addAttribute("medicos", medicos);

        return "medicosyespecialidades";
    }
}
