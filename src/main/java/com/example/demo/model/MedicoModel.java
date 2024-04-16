//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import java.util.Date;

public class MedicoModel {
	
    private int id;
    private String nombre;
    private String apellidos;
    private int edad;
    private Date fechaalta;
    private String username;
    private String password;
    private EspecialidadModel especialidad;
    
	public MedicoModel(int id, String nombre, String apellidos, int edad, Date fechaalta, String username,
			String password, EspecialidadModel especialidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.fechaalta = fechaalta;
		this.username = username;
		this.password = password;
		this.especialidad = especialidad;
	}
	
	public MedicoModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EspecialidadModel getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(EspecialidadModel especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "MedicoModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad
				+ ", fechaalta=" + fechaalta + ", username=" + username + ", password=" + password + ", especialidad="
				+ especialidad + "]";
	}
	
	
    
    
}
