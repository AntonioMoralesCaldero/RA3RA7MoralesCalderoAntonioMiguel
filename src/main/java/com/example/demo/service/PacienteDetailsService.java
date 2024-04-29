package com.example.demo.service;

import com.example.demo.entity.Paciente;
import com.example.demo.model.PacienteModel;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.security.PacientePrincipal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PacienteDetailsService implements UserDetailsService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Paciente paciente = pacienteRepository.findByUsername(username);
        if (paciente == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        PacienteModel pacienteModel = modelMapper.map(paciente, PacienteModel.class);
        return new PacientePrincipal(pacienteModel);
    }

    // Otros métodos de servicio
}

