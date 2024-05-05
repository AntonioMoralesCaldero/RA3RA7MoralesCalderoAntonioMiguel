// Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;
import com.example.demo.entity.Medicamento;
import com.example.demo.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicamentoDashboard")
public class MedicamentoController {
    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping("/")
    public String listarMedicamentos(Model model) {
        model.addAttribute("medicamentos", medicamentoService.findAll());
        return "listaMedicamentos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoMedicamento(Model model) {
        model.addAttribute("medicamento", new Medicamento());
        return "formularioMedicamento";
    }

    @PostMapping("/guardar")
    public String guardarMedicamento(@ModelAttribute("medicamento") Medicamento medicamento) {
        medicamentoService.save(medicamento);
        return "redirect:/medicamentoDashboard/";
    }



    @GetMapping("/editar/{id}")
    public String editarMedicamento(@PathVariable int id, Model model) {
        Medicamento medicamento = medicamentoService.findById(id);
        model.addAttribute("medicamento", medicamento);
        return "formularioMedicamento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMedicamento(@PathVariable int id) {
        medicamentoService.delete(id);
        return "redirect:/medicamentoDashboard/";
    }
}
