package com.edutech.pagos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pagos", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usuario", "fecha", "monto"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    @Min(value = 1, message = "El monto debe ser mayor a 0")
    private double monto;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodo;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    private String codigoCupon;

    private double montoFinal;
}
