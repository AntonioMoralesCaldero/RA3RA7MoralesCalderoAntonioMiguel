package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Paciente;
import com.example.demo.repository.PacienteRepository;

@Service
public class PacienteDetailsService implements UserDetailsService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Paciente paciente = pacienteRepository.findByUsername(username);
        if (paciente == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(paciente.getUsername(), paciente.getPassword(), new ArrayList<>());
    }

}

