//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

public class MedicamentoModel {
	
	private int id;
    private String nombre;
    private String descripcion;
    private char receta;
    private float precio;
    private int stock;
    
	public MedicamentoModel(int id, String nombre, String descripcion, char receta, float precio, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.receta = receta;
		this.precio = precio;
		this.stock = stock;
	}
    
    public MedicamentoModel() {
    	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public char getReceta() {
		return receta;
	}

	public void setReceta(char receta) {
		this.receta = receta;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "MedicamentoModel [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", receta="
				+ receta + ", precio=" + precio + ", stock=" + stock + "]";
	}
    
    

}
