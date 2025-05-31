package com.edutech.pagos.repository;

import com.edutech.pagos.model.Pago;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    boolean existsByUsuarioAndFechaAndMonto(String usuario, LocalDate fecha, double monto);
}
