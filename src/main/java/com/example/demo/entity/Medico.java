//Autor: Antonio Miguel Morales Caldero
package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "apellidos", length = 50)
    private String apellidos;

    @Column(name = "edad")
    private int edad;

    @Column(name = "fechaalta")
    private Date fechaalta;

    @Column(name = "username", length = 30)
    private String username;

    @Column(name = "password", length = 30)
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "idEspecialidad")
    private Especialidad especialidad;

	public void setPassword(String encode) {
		
	}
}