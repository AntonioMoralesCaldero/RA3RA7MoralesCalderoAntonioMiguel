//Autor: Antonio Miguel Morales Caldero
package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "precio")
    private float precio;

    @Column(name = "dispensada")
    private boolean dispensada;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<CompraMedicamento> compraMedicamentos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isDispensada() {
        return dispensada;
    }

    public void setDispensada(boolean dispensada) {
        this.dispensada = dispensada;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<CompraMedicamento> getCompraMedicamentos() {
        return compraMedicamentos;
    }

    public void setCompraMedicamentos(List<CompraMedicamento> compraMedicamentos) {
        this.compraMedicamentos = compraMedicamentos;
    }
}
