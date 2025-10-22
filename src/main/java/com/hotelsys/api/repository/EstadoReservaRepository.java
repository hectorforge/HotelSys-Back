package com.hotelsys.api.repository;

import com.hotelsys.api.model.catalogos.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoReservaRepository extends JpaRepository<EstadoReserva, Integer> {
    Optional<EstadoReserva> findByDescripcionIgnoreCase(String descripcion);
}
