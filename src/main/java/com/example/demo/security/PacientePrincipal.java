package com.example.demo.security;

import com.example.demo.model.PacienteModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PacientePrincipal implements UserDetails {

    private PacienteModel pacienteModel;

    public PacientePrincipal(PacienteModel pacienteModel) {
        this.pacienteModel = pacienteModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes asignar roles al usuario (paciente)
        return Collections.singletonList(new SimpleGrantedAuthority("ROL_PACIENTE"));
    }

    @Override
    public String getPassword() {
        return pacienteModel.getPassword();
    }

    @Override
    public String getUsername() {
        return pacienteModel.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
