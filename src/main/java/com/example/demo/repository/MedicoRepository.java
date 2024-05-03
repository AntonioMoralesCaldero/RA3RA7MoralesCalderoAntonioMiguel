package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Medico findByUsername(String username);
}