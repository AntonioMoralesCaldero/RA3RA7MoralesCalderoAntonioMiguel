//Autor: Antonio Miguel Morales Caldero
package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import com.example.demo.entity.Compra;

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
    private boolean isActive;
    private Double totalGasto;
    private List<Compra> compras;

    public PacienteModel(int id, String nombre, String apellidos, int edad, String direccion, MultipartFile foto,
                         String username, String password, boolean isActive) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.direccion = direccion;
        this.foto = foto;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.totalGasto = 0.0;
    }

    public PacienteModel() {
        this.isActive = true;
        this.totalGasto = 0.0;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(Double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    @Override
    public String toString() {
        return "PacienteModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad
                + ", direccion=" + direccion + ", foto=" + foto + ", fotoFilename=" + fotoFilename + ", username="
                + username + ", password=" + password + ", isActive=" + isActive + ", totalGasto=" + totalGasto
                + ", compras=" + compras + "]";
    }
}