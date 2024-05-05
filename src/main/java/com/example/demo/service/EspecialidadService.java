//Autor: Antonio Miguel Morales Caldero
package com.example.demo.service;

import com.example.demo.entity.Especialidad;
import java.util.List;

public interface EspecialidadService {
    List<Especialidad> findAll();
    Especialidad findById(int id);
    void save(Especialidad especialidad);
    void delete(int id);
    
}
