//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Paciente;
import com.example.demo.model.PacienteModel;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.service.PacienteService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("pacienteService")
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public PacienteModel registrar(PacienteModel pacienteModel) {
        Paciente paciente = new Paciente();
        
        System.out.printf("Setting entity foto to: %s%n", pacienteModel.getFotoFilename());
        
        paciente.setNombre(pacienteModel.getNombre());
        paciente.setApellidos(pacienteModel.getApellidos());
        paciente.setEdad(pacienteModel.getEdad());
        paciente.setDireccion(pacienteModel.getDireccion());
        paciente.setUsername(pacienteModel.getUsername());
        paciente.setPassword(passwordEncoder.encode(pacienteModel.getPassword()));
        paciente.setFoto(pacienteModel.getFotoFilename()); // Assign filename to the entity
        
        Paciente savedPaciente = pacienteRepository.save(paciente);
        
        System.out.printf("Saved entity with foto: %s%n", savedPaciente.getFoto());
        
        PacienteModel resultModel = new PacienteModel();
        resultModel.setNombre(savedPaciente.getNombre());
        resultModel.setApellidos(savedPaciente.getApellidos());
        resultModel.setEdad(savedPaciente.getEdad());
        resultModel.setDireccion(savedPaciente.getDireccion());
        resultModel.setUsername(savedPaciente.getUsername());
        resultModel.setFotoFilename(savedPaciente.getFoto());
        System.out.printf("Returning model with foto: %s%n", resultModel.getFotoFilename());

        return resultModel;
    }


    @Override
    public boolean authenticate(String username, String password) {
        Paciente paciente = pacienteRepository.findByUsername(username);
        return paciente != null && passwordEncoder.matches(password, paciente.getPassword());
    }
    
    @Override
    public List<PacienteModel> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteModel> pacienteModels = new ArrayList<>();
        
        for (Paciente paciente : pacientes) {
            PacienteModel model = new PacienteModel();
            model.setId(paciente.getId());
            model.setNombre(paciente.getNombre());
            model.setApellidos(paciente.getApellidos());
            model.setEdad(paciente.getEdad());
            model.setDireccion(paciente.getDireccion());
            model.setUsername(paciente.getUsername());
            model.setActive(paciente.isActive());
            model.setFotoFilename(paciente.getFoto());

            System.out.printf("Mapped PacienteModel: %s, Foto: %s%n", model.getNombre(), model.getFotoFilename());

            pacienteModels.add(model);
        }
        return pacienteModels;
    }


    @Override
    public void togglePacienteActive(int id) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null) {
            paciente.setActive(!paciente.isActive());
            pacienteRepository.save(paciente);
        }
    }
    
    @Override
    public PacienteModel findById(int id) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente == null) return null;

        PacienteModel model = new PacienteModel();
        model.setId(paciente.getId());
        model.setNombre(paciente.getNombre());
        model.setApellidos(paciente.getApellidos());
        model.setEdad(paciente.getEdad());
        model.setDireccion(paciente.getDireccion());
        model.setUsername(paciente.getUsername());
        model.setActive(paciente.isActive());
        model.setFotoFilename(paciente.getFoto());

        return model;
    }

    @Override
    public void updatePaciente(int id, PacienteModel pacienteModel) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null) {
            paciente.setNombre(pacienteModel.getNombre());
            paciente.setApellidos(pacienteModel.getApellidos());
            paciente.setEdad(pacienteModel.getEdad());
            paciente.setDireccion(pacienteModel.getDireccion());
            paciente.setUsername(pacienteModel.getUsername());
            paciente.setActive(pacienteModel.isActive());

            if (pacienteModel.getFotoFilename() != null) {
                paciente.setFoto(pacienteModel.getFotoFilename());
            }

            pacienteRepository.save(paciente);
        }
    }


    @Override
    public void deleteById(int id) {
        pacienteRepository.deleteById(id);
    }

}
