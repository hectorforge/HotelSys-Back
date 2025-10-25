package com.hotelsys.api.controller;

import com.hotelsys.api.dto.ReservaRequest;
import com.hotelsys.api.model.entidades.Reserva;
import com.hotelsys.api.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllActiveReservas() {
        return ResponseEntity.ok(reservaService.getAllActiveReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Integer id) {
        return reservaService.getReservaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createReserva(@RequestBody ReservaRequest reservaRequest) {
        try {
            Reserva nuevaReserva = reservaService.createReserva(reservaRequest);
            return ResponseEntity.ok(nuevaReserva);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Error al crear la reserva");
            error.put("detalle", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PatchMapping("/cancelar/{id}")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Integer id) {
        return reservaService.deleteLogicoReserva(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Integer id, @RequestBody ReservaRequest reservaRequest) {
        try {
            return reservaService.updateReserva(id, reservaRequest)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
