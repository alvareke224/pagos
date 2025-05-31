package com.edutech.pagos.controller;

import com.edutech.pagos.model.Pago;
import com.edutech.pagos.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private PagoService servicio;

    @GetMapping
    public List<Pago> listar() {
        return servicio.listar();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Pago pago) {
        try {
            Pago nuevo = servicio.guardar(pago);
            return ResponseEntity.ok(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Long id) {
        Pago pago = servicio.buscarPorId(id);
        return pago != null ? ResponseEntity.ok(pago) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Pago pago) {
        Pago actualizado = servicio.actualizar(id, pago);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }
}
