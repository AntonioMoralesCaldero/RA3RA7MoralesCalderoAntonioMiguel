//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Medico;
import com.example.demo.model.MedicoModel;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.service.MedicoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService {

	private final MedicoRepository medicoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicoServiceImpl(MedicoRepository medicoRepository, ModelMapper modelMapper) {
        this.medicoRepository = medicoRepository;
        this.modelMapper = modelMapper;
    }

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
    
    @Override
    public Medico findByUsername(String username) {
        return medicoRepository.findByUsername(username);
    }
    
    @Override
    public List<MedicoModel> findAllOrderByCitasDesc() {
        return medicoRepository.findAll().stream()
            .sorted((m1, m2) -> Integer.compare(m2.getCitas().size(), m1.getCitas().size()))
            .map(medico -> {
                MedicoModel model = modelMapper.map(medico, MedicoModel.class);
                model.setNumeroCitas((long) medico.getCitas().size());
                return model;
            })
            .collect(Collectors.toList());
    }
}
