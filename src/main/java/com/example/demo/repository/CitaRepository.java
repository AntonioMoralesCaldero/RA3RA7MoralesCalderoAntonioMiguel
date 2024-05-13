//Autor: Antonio Miguel Morales Caldero
package com.example.demo.repository;

import com.example.demo.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
	int countByMedicoIdAndFecha(int medicoId, Date fechaHora);
    List<Cita> findByMedicoIdAndFecha(int medicoId, Date fecha);
    
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.medico.id = :medicoId AND c.fecha >= :startOfDay AND c.fecha < :endOfDay")
    int countCitasByDay(@Param("medicoId") int medicoId, @Param("startOfDay") Date startOfDay, @Param("endOfDay") Date endOfDay);

    @Query("SELECT COUNT(c) FROM Cita c WHERE c.medico.id = :medicoId AND c.fecha BETWEEN :startTime AND :endTime")
    int countCitasByTimeSlot(@Param("medicoId") int medicoId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

}

