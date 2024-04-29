package com.example.demo.controller;

import com.example.demo.entity.Paciente;
import com.example.demo.model.PacienteModel;
import com.example.demo.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm() {
		return "register";
        
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute PacienteModel pacienteModel) {
        // Codifica la contraseña antes de guardarla
        pacienteModel.setPassword(passwordEncoder.encode(pacienteModel.getPassword()));
        // Convierte PacienteModel a la entidad Paciente
        Paciente paciente = modelMapper.map(pacienteModel, Paciente.class);
        // Guarda el nuevo usuario en la base de datos
        pacienteRepository.save(paciente);
        // Redirige al usuario a la página de inicio de sesión
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
