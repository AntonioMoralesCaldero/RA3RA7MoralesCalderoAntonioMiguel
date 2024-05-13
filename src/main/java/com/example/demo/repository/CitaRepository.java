//Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
	int countByMedicoIdAndFecha(int medicoId, Date fechaHora);
    List<Cita> findByMedicoIdAndFecha(int medicoId, Date fecha);

}

