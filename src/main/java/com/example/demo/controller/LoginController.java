package com.example.demo.controller;

import com.example.demo.model.PacienteModel;
import com.example.demo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final PacienteService pacienteService;

    @Autowired
    public LoginController(@Qualifier("pacienteService") PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("pacienteModel", new PacienteModel());
        return "register";
    }

    @PostMapping("/auth/register")
    public String registerForm(@ModelAttribute("pacienteModel") PacienteModel pacienteModel,
                               RedirectAttributes flash) {
        pacienteService.registrar(pacienteModel);
        flash.addFlashAttribute("success", "Paciente registrado con éxito!!");
        return "redirect:/auth/login";
    }
    
    @GetMapping("/auth/login")
    public String showLoginForm(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if (logout != null) {
            model.addAttribute("message", "You have been successfully logged out.");
        }
        return "login";
    }

}
