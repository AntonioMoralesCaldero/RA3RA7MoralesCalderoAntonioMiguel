package com.example.demo.model;

public class EspecialidadModel {

    private int id;
    private String nombre;
    
	public EspecialidadModel(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
    
    public EspecialidadModel() {
    	
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
    
    
}
