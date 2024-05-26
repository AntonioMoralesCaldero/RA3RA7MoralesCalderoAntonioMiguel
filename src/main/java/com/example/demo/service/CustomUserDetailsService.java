//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
            return new CustomUserDetails(paciente.getUsername(), paciente.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_PACIENTE")), paciente.getId());
        }

        Medico medico = medicoRepository.findByUsername(username);
        if (medico != null) {
            return new CustomUserDetails(medico.getUsername(), medico.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEDICO")), medico.getId());
        }

        Administrador administrador = administradorRepository.findByUsername(username);
        if (administrador != null) {
            return new CustomUserDetails(administrador.getUsername(), administrador.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")), administrador.getId());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
