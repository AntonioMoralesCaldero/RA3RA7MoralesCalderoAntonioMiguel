//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service.impl;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Paciente;
import com.example.demo.model.PacienteModel;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.service.PacienteService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("pacienteService")
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PacienteModel registrar(PacienteModel pacienteModel) {
        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteModel.getNombre());
        paciente.setApellidos(pacienteModel.getApellidos());
        paciente.setEdad(pacienteModel.getEdad());
        paciente.setDireccion(pacienteModel.getDireccion());
        paciente.setUsername(pacienteModel.getUsername());
        paciente.setPassword(passwordEncoder.encode(pacienteModel.getPassword()));
        paciente.setFoto(pacienteModel.getFotoFilename());

        Paciente savedPaciente = pacienteRepository.save(paciente);

        return convertToModel(savedPaciente);
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
            PacienteModel model = convertToModel(paciente);
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
        return (paciente != null) ? convertToModel(paciente) : null;
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

    @Override
    public Paciente findByUsername(String username) {
        return pacienteRepository.findByUsername(username);
    }

    @Override
    public PacienteModel login(String username, String password) {
        Paciente paciente = pacienteRepository.findByUsername(username);
        if (paciente != null && passwordEncoder.matches(password, paciente.getPassword())) {
            return convertToModel(paciente);
        }
        return null;
    }

    @Override
    public List<PacienteModel> findAllOrderByGastoDesc() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteModel> pacienteModels = new ArrayList<>();

        for (Paciente paciente : pacientes) {
            PacienteModel model = convertToModel(paciente);
            model.setTotalGasto(calculaTotalGasto(paciente));
            pacienteModels.add(model);
        }

        pacienteModels.sort((p1, p2) -> p2.getTotalGasto().compareTo(p1.getTotalGasto()));

        return pacienteModels;
    }

    @Override
    public List<PacienteModel> findAllOrderByCitasEspecialidad(int especialidadId) {
        List<Paciente> pacientes = pacienteRepository.findAllOrderByCitasEspecialidad(especialidadId);
        List<PacienteModel> pacienteModels = new ArrayList<>();

        for (Paciente paciente : pacientes) {
            PacienteModel model = convertToModel(paciente);
            model.setNumeroCitas((long) paciente.getCitas().size());
            pacienteModels.add(model);
        }

        pacienteModels.sort((p1, p2) -> p2.getNumeroCitas().compareTo(p1.getNumeroCitas()));

        return pacienteModels;
    }






    private PacienteModel convertToModel(Paciente paciente) {
        PacienteModel model = new PacienteModel();
        model.setId(paciente.getId());
        model.setNombre(paciente.getNombre());
        model.setApellidos(paciente.getApellidos());
        model.setEdad(paciente.getEdad());
        model.setDireccion(paciente.getDireccion());
        model.setFotoFilename(paciente.getFoto());
        model.setUsername(paciente.getUsername());
        model.setPassword(paciente.getPassword());
        model.setActive(paciente.isActive());
        model.setCompras(paciente.getCompras());
        return model;
    }

    private Double calculaTotalGasto(Paciente paciente) {
        double totalGasto = 0.0;
        for (Compra compra : paciente.getCompras()) {
            if (compra.isDispensada()) {
                totalGasto += compra.getPrecio();
            }
        }
        return totalGasto;
    }
}
