//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Medico;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico save(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public Medico findById(Integer id) {
        return medicoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Medico medico) {
        medicoRepository.delete(medico);
    }
    
    @Override
    public List<Medico> findByEspecialidad(int especialidadId) {
        return medicoRepository.findByEspecialidadId(especialidadId);
    }
}
