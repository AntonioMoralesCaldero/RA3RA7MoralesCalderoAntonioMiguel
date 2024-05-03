package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.PacienteService;

@Controller
public class AdminController {
	
	@Autowired
    private PacienteService pacienteService;

    @GetMapping("/adminDashboard")
    public String adminDashboard(Model model) {
        return "adminDashboard";
    }
    
    @GetMapping("/gestionPacientes")
    public String gestionPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.findAll());
        return "gestionPacientes";
    }

    @PostMapping("/activarDesactivarPaciente/{id}")
    public String activarDesactivarPaciente(@PathVariable int id) {
        pacienteService.togglePacienteActive(id);
        return "redirect:/adminDashboard/gestionPacientes";
    }
}
