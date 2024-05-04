//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.model.PacienteModel;
import com.example.demo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/adminDashboard")
    public String adminDashboard(Model model) {
        return "adminDashboard";
    }
    
    @GetMapping("/adminDashboard/gestionPacientes")
    public String gestionPacientes(Model model) {
        List<PacienteModel> pacientes = pacienteService.findAll();
        model.addAttribute("pacientes", pacientes);
        return "gestionPacientes";
    }

    @PostMapping("/adminDashboard/activarDesactivarPaciente/{id}")
    public String activarDesactivarPaciente(@PathVariable int id) {
        pacienteService.togglePacienteActive(id);
        return "redirect:/adminDashboard/gestionPacientes";
    }

    @GetMapping("/adminDashboard/editarPaciente/{id}")
    public String editarPaciente(@PathVariable int id, Model model) {
        PacienteModel paciente = pacienteService.findById(id);
        model.addAttribute("paciente", paciente);
        return "editarPaciente";
    }

    @PostMapping("/adminDashboard/actualizarPaciente/{id}")
    public String actualizarPaciente(@PathVariable int id,
                                     @ModelAttribute PacienteModel pacienteModel,
                                     @RequestParam("foto") MultipartFile file) {
        
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;
                Path imgDirPath = Paths.get("src", "main", "resources", "static", "imgs");
                Path filePath = imgDirPath.resolve(uniqueFilename);
                Files.createDirectories(imgDirPath);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                pacienteModel.setFotoFilename(uniqueFilename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PacienteModel existingPaciente = pacienteService.findById(id);
        if (existingPaciente != null) {
            existingPaciente.setDireccion(pacienteModel.getDireccion());
            existingPaciente.setUsername(pacienteModel.getUsername());

            existingPaciente.setNombre(pacienteModel.getNombre());
            existingPaciente.setApellidos(pacienteModel.getApellidos());
            existingPaciente.setEdad(pacienteModel.getEdad());

            if (pacienteModel.getFotoFilename() != null) {
                existingPaciente.setFotoFilename(pacienteModel.getFotoFilename());
            }

            pacienteService.updatePaciente(id, existingPaciente);
        }

        return "redirect:/adminDashboard/gestionPacientes";
    }




    @PostMapping("/adminDashboard/eliminarPaciente/{id}")
    public String eliminarPaciente(@PathVariable int id) {
        pacienteService.deleteById(id);
        return "redirect:/adminDashboard/gestionPacientes";
    }
}
