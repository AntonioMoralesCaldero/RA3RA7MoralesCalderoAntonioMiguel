package com.example.demo.model;

import java.util.Date;

public class CompraModel {

    private int id;
    private Date fecha;
    private float precio;
    private boolean dispensada;
    private PacienteModel paciente;
    
    public CompraModel(int id, Date fecha, float precio, boolean dispensada, PacienteModel paciente) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.precio = precio;
        this.dispensada = dispensada;
        this.paciente = paciente;
    }
    
    public CompraModel() {
        
    }

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

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "CompraModel [id=" + id + ", fecha=" + fecha + ", precio=" + precio + ", dispensada=" + dispensada + ", paciente=" + paciente + "]";
    }
}
