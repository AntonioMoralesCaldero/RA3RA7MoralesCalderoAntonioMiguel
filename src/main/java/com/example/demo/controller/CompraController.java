//Autor: Antonio Miguel Morales Caldero
package com.example.demo.controller;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Medicamento;
import com.example.demo.entity.Paciente;
import com.example.demo.model.PacienteModel;
import com.example.demo.service.CompraService;
import com.example.demo.service.MedicamentoService;
import com.example.demo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private CompraService compraService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/medicamentos")
    public String listarMedicamentos(@RequestParam(value = "nombre", required = false) String nombre, Model model, HttpSession session) {
        List<Medicamento> medicamentos = (nombre != null && !nombre.isEmpty())
                ? medicamentoService.findByNombreContaining(nombre)
                : medicamentoService.findAll();
        model.addAttribute("medicamentos", medicamentos);
        model.addAttribute("carrito", session.getAttribute("carrito"));
        return "medicamentos";
    }

    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam("medicamentoId") int medicamentoId, HttpSession session) {
        Medicamento medicamento = medicamentoService.findById(medicamentoId);
        if (medicamento != null) {
            List<Medicamento> carrito = (List<Medicamento>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new ArrayList<>();
            }
            carrito.add(medicamento);
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/compras/medicamentos";
    }

    @PostMapping("/carrito/comprar")
    public String comprarCarrito(HttpSession session) {
        Integer pacienteId = (Integer) session.getAttribute("pacienteId");
        if (pacienteId == null) {
            return "redirect:/compras/medicamentos";
        }

        PacienteModel pacienteModel = pacienteService.findById(pacienteId);
        if (pacienteModel == null) {
            return "redirect:/compras/medicamentos";
        }

        Paciente paciente = convertToEntity(pacienteModel);

        List<Medicamento> carrito = (List<Medicamento>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            return "redirect:/compras/medicamentos";
        }

        Compra compra = new Compra();
        compra.setFecha(new Date());
        compra.setPaciente(paciente);
        float total = 0;
        for (Medicamento medicamento : carrito) {
            total += medicamento.getPrecio();
        }
        compra.setPrecio(total);
        compraService.save(compra);

        session.removeAttribute("carrito");
        return "redirect:/compras/historico";
    }

    @GetMapping("/historico")
    public String verHistorico(Model model, HttpSession session) {
        Integer pacienteId = (Integer) session.getAttribute("pacienteId");
        if (pacienteId == null) {
            return "redirect:/perfil";
        }
        model.addAttribute("compras", compraService.findByPacienteId(pacienteId));
        return "historico";
    }

    private Paciente convertToEntity(PacienteModel model) {
        Paciente entity = new Paciente();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setApellidos(model.getApellidos());
        entity.setEdad(model.getEdad());
        entity.setDireccion(model.getDireccion());
        entity.setFoto(model.getFotoFilename());
        entity.setUsername(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setActive(model.isActive());
        return entity;
    }
}
