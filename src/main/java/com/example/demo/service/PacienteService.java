//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.model.PacienteModel;

import java.util.List;

public interface PacienteService {
    PacienteModel registrar(PacienteModel pacienteModel);
    boolean authenticate(String username, String password);
    List<PacienteModel> findAll();
    void togglePacienteActive(int id);
    PacienteModel findById(int id);
    void updatePaciente(int id, PacienteModel pacienteModel);
    void deleteById(int id);
}
