package com.example.demo.service.impl;

import com.example.demo.entity.Paciente;
import com.example.demo.model.PacienteModel;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.service.PacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("pacienteService")
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    
    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PacienteModel registrar(PacienteModel pacienteModel) {
        Paciente paciente = convertToEntity(pacienteModel);

        // Codificar la contraseña antes de guardarla
        paciente.setPassword(passwordEncoder.encode(paciente.getPassword()));

        // Guardar el paciente en la base de datos
        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        return convertToModel(pacienteGuardado);
    }

    private Paciente convertToEntity(PacienteModel pacienteModel) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pacienteModel, Paciente.class);
    }

    private PacienteModel convertToModel(Paciente paciente) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paciente, PacienteModel.class);
    }
    
}
