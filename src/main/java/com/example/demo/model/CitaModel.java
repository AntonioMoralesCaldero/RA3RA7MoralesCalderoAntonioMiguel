//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import java.util.Date;

public class CitaModel {

    private int id;
    private PacienteModel paciente;
    private MedicoModel medico;
    private Date fecha;
    private String observaciones;
    
	public CitaModel(int id, PacienteModel paciente, MedicoModel medico, Date fecha, String observaciones) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.fecha = fecha;
		this.observaciones = observaciones;
	}
    
    public CitaModel() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PacienteModel getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteModel paciente) {
		this.paciente = paciente;
	}

	public MedicoModel getMedico() {
		return medico;
	}

	public void setMedico(MedicoModel medico) {
		this.medico = medico;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "CitaModel [id=" + id + ", paciente=" + paciente + ", medico=" + medico + ", fecha=" + fecha
				+ ", observaciones=" + observaciones + "]";
	}
    
    
    
}
