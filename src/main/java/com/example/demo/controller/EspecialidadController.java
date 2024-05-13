//Autor: Antonio Miguel Morales Caldero

package com.example.demo.controller;

import com.example.demo.entity.Especialidad;
import com.example.demo.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/")
    public String listarEspecialidades(Model model) {
        model.addAttribute("especialidades", especialidadService.findAll());
        return "listaEspecialidades";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevaEspecialidad(Model model) {
        model.addAttribute("especialidad", new Especialidad());
        return "formularioEspecialidad";
    }

    @PostMapping("/guardar")
    public String guardarEspecialidad(@ModelAttribute("especialidad") Especialidad especialidad) {
        especialidadService.save(especialidad);
        return "redirect:/especialidades/";
    }

    @GetMapping("/editar/{id}")
    public String editarEspecialidad(@PathVariable int id, Model model) {
        Especialidad especialidad = especialidadService.findById(id);
        model.addAttribute("especialidad", especialidad);
        return "formularioEspecialidad";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEspecialidad(@PathVariable int id) {
        especialidadService.delete(id);
        return "redirect:/especialidades/";
    }
}
