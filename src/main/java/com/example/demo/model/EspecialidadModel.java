//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

public class EspecialidadModel {
    private int id;
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
        if (esEspecialidadValida(nombre)) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Especialidad no válida");
        }
    }
}
