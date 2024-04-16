//Autor: Antonio Miguel Morales Caldero
package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    public enum TipoEspecialidad {
        MEDICO_DE_FAMILIA,
        DIGESTIVO,
        NEUROLOGO,
        DERMATOLOGO,
        TRAUMATOLOGO
    }

    public static boolean esEspecialidadValida(String nombreEspecialidad) {
        for (TipoEspecialidad tipo : TipoEspecialidad.values()) {
            if (tipo.name().equalsIgnoreCase(nombreEspecialidad)) {
                return true;
            }
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (esEspecialidadValida(nombre)) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Especialidad no válida");
        }
    }
}
