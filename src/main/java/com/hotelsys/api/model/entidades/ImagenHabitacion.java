package com.hotelsys.api.model.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "imagenes_habitaciones")
public class ImagenHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "alt")
    private String alt;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "orden")
    private Integer orden;


    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable = false)
    @JsonBackReference
    private Habitacion habitacion;
}
