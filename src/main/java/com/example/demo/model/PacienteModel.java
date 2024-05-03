//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class PacienteModel {
    
    private int id;
    private String nombre;
    private String apellidos;
    private int edad;
    private String direccion;
    private MultipartFile foto;
    private String fotoFilename;
    private String username;
    private String password;
    
    public PacienteModel(int id, String nombre, String apellidos, int edad, String direccion, MultipartFile foto,
            String username, String password) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.direccion = direccion;
        this.foto = foto;
        this.username = username;
        this.password = password;
    }

    public PacienteModel() {
        
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }

    public String getFotoFilename() {
        return fotoFilename;
    }

    public void setFotoFilename(String fotoFilename) {
        this.fotoFilename = fotoFilename;
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

    @Override
    public String toString() {
        return "PacienteModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad
                + ", direccion=" + direccion + ", foto=" + foto + ", fotoFilename=" + fotoFilename + ", username=" + username + ", password=" + password
                + "]";
    }
}
