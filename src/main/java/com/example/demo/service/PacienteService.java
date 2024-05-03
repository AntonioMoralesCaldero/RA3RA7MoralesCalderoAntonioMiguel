package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PacienteModel;

public interface PacienteService {
    PacienteModel registrar(PacienteModel pacienteModel);
	boolean authenticate(String username, String password);
	List<PacienteModel> findAll();
    void togglePacienteActive(int id);
}
