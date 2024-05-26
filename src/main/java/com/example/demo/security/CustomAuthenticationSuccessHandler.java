//Autor: Antonio Miguel Morales Caldero
package com.example.demo.security;

import com.example.demo.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer pacienteId = userDetails.getId();

        session.setAttribute("pacienteId", pacienteId);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            response.sendRedirect("/adminDashboard");
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_MEDICO"))) {
            response.sendRedirect("/perfilDeMedicos");
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_PACIENTE"))) {
            response.sendRedirect("/perfil");
        } else {
            response.sendRedirect("/auth/login");
        }
    }
}
