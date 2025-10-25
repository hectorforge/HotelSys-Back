package com.hotelsys.api.repository;

import com.hotelsys.api.model.entidades.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    @Query("SELECT DISTINCT h FROM Habitacion h " +
            "LEFT JOIN FETCH h.tipoHabitacion " +
            "LEFT JOIN FETCH h.estadoHabitacion " +
            "LEFT JOIN FETCH h.imagenes")
    List<Habitacion> findAllWithImages();

    List<Habitacion> findByActivoTrue();
    Optional<Habitacion> findByNumero(String numero);
    List<Habitacion> findByIdIn(List<Integer> ids);
}
