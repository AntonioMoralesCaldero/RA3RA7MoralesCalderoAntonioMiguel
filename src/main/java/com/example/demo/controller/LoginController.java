//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.model.PacienteModel;
import com.example.demo.service.PacienteService;

import jakarta.servlet.http.HttpSession;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public String registerForm(@ModelAttribute("pacienteModel") PacienteModel pacienteModel, RedirectAttributes flash) {
        try {
            MultipartFile file = pacienteModel.getFoto();
            if (file != null && !file.isEmpty()) {
                String filename = savePhoto(file);
                pacienteModel.setFotoFilename(filename);
                
                System.out.printf("Setting fotoFilename: %s%n", pacienteModel.getFotoFilename());
            }

            pacienteService.registrar(pacienteModel);
            flash.addFlashAttribute("success", "Paciente registrado con éxito!!");
            return "redirect:/auth/login";
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al registrar el paciente: " + e.getMessage());
            return "redirect:/auth/register";
        }
    }


    @GetMapping("/auth/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Usuario o contraseña inválido");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Has cerrado sesión correctamente");
        }
        return "login";
    }
    
    @PostMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, RedirectAttributes flash) {
        PacienteModel pacienteModel = pacienteService.login(username, password);
        if (pacienteModel != null) {
            session.setAttribute("pacienteId", pacienteModel.getId());
            return "redirect:/compras/medicamentos";
        } else {
            flash.addFlashAttribute("errorMessage", "Usuario o contraseña inválido");
            return "redirect:/auth/login";
        }
    }

    
    private String savePhoto(MultipartFile file) throws Exception {
        String directoryPath = "src/main/resources/static/imgs/";
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(directoryPath + filename);
        Files.write(path, file.getBytes());
        return filename;
    }


}