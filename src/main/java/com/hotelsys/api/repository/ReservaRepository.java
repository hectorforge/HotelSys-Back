package com.hotelsys.api.repository;

import com.hotelsys.api.model.catalogos.EstadoReserva;
import com.hotelsys.api.model.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByActivoTrue();

    List<Reserva> findByCliente_Email(String email);


}
