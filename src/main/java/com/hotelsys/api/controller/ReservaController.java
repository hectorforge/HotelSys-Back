package com.hotelsys.api.controller;

import com.hotelsys.api.dto.ReservaRequest;
import com.hotelsys.api.model.entidades.Reserva;
import com.hotelsys.api.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Reserva> createReserva(@RequestBody ReservaRequest reservaRequest) {
        try {
            Reserva nuevaReserva = reservaService.createReserva(reservaRequest);
            return ResponseEntity.ok(nuevaReserva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
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

    @PatchMapping("/cancelar/{id}")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Integer id) {
        return reservaService.deleteLogicoReserva(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{idRes}/cancelar/{emailCli:.+}")
    public ResponseEntity<Reserva> cancelarReservaPorIdYEmail(
            @PathVariable Integer idRes,
            @PathVariable String emailCli) {

        return reservaService.cancelarReservaPorIdYEmail(idRes, emailCli)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  por email
    @GetMapping("/cliente")
    public ResponseEntity<List<Reserva>> getAllReservasByClienteEmail(@RequestParam String email) {
        List<Reserva> reservas = reservaService.getAllReservasByClienteEmail(email);
        return reservas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(reservas);
    }
}
