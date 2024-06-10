//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Medico;
import com.example.demo.model.MedicoModel;

import java.util.List;

public interface MedicoService {
    List<Medico> findAll();
    Medico save(Medico medico);
    Medico findById(Integer id);
    void delete(Medico medico);
    List<Medico> findByEspecialidad(int especialidadId);
    Medico findByUsername(String username);
    List<MedicoModel> findAllOrderByCitasDesc();

}
