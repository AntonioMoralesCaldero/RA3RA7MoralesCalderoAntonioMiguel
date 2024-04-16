package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamento")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "receta", length = 1)
    private String receta;

    @Column(name = "precio")
    private float precio;

    @Column(name = "stock")
    private int stock;

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        if (recetaValida(receta)) {
            this.receta = receta;
        } else {
            throw new IllegalArgumentException("Valor de receta no válido");
        }
    }

    private boolean recetaValida(String receta) {
        return receta != null && (receta.equals("s") || receta.equals("n"));
    }
}
