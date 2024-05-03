package com.example.demo.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Paciente;
import com.example.demo.entity.Medico;
import com.example.demo.entity.Administrador;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.AdministradorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Paciente paciente = pacienteRepository.findByUsername(username);
        if (paciente != null) {
            return new User(paciente.getUsername(), paciente.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_PACIENTE")));
        }

        Medico medico = medicoRepository.findByUsername(username);
        if (medico != null) {
            return new User(medico.getUsername(), medico.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEDICO")));
        }

        Administrador administrador = administradorRepository.findByUsername(username);
        if (administrador != null) {
            return new User(administrador.getUsername(), administrador.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
