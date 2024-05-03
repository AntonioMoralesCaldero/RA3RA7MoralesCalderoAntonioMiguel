package com.example.demo.service.impl;

import com.example.demo.entity.Paciente;
import com.example.demo.model.PacienteModel;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.service.PacienteService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("pacienteService")
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public PacienteModel registrar(PacienteModel pacienteModel) {
        Paciente paciente = modelMapper.map(pacienteModel, Paciente.class);
        paciente.setPassword(passwordEncoder.encode(paciente.getPassword()));
        paciente.setFoto(pacienteModel.getFotoFilename()); // Guarda solo el nombre del archivo
        Paciente savedPaciente = pacienteRepository.save(paciente);
        return modelMapper.map(savedPaciente, PacienteModel.class);
    }

    
    @Override
    public boolean authenticate(String username, String password) {
        Paciente paciente = pacienteRepository.findByUsername(username);
        return paciente != null && passwordEncoder.matches(password, paciente.getPassword());
    }
    
    @Override
    public List<PacienteModel> findAll() {
        return pacienteRepository.findAll().stream()
            .map(paciente -> modelMapper.map(paciente, PacienteModel.class))
            .collect(Collectors.toList());
    }

    @Override
    public void togglePacienteActive(int id) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null) {
            paciente.setActive(!paciente.isActive());  // Cambia el estado de activo a inactivo o viceversa
            pacienteRepository.save(paciente);
        }
    }

}
