package com.example.demo.repository;

import com.example.demo.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Paciente findByUsername(String username);
}
