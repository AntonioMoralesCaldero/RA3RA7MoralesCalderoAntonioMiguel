//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

public class CompraMedicamentoModel {

    private int id;
    private MedicamentoModel medicamento;
    private CompraModel compra;
    
	public CompraMedicamentoModel(int id, MedicamentoModel medicamento, CompraModel compra) {
		super();
		this.id = id;
		this.medicamento = medicamento;
		this.compra = compra;
	}
    
    public CompraMedicamentoModel() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MedicamentoModel getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(MedicamentoModel medicamento) {
		this.medicamento = medicamento;
	}

	public CompraModel getCompra() {
		return compra;
	}

	public void setCompra(CompraModel compra) {
		this.compra = compra;
	}

	@Override
	public String toString() {
		return "CompraMedicamentoModel [id=" + id + ", medicamento=" + medicamento + ", compra=" + compra + "]";
	}
    
    

}
