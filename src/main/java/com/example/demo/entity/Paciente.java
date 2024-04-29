//Autor: Antonio Miguel Morales Caldero
package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "apellidos", length = 50)
    private String apellidos;

    @Column(name = "edad")
    private int edad;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "foto", length = 100)
    private String foto;

    @Column(name = "username", length = 30)
    private String username;

    @Column(name = "password", length = 30)
    private String password;

	public void setPassword(String encode) {
		
	}
}
