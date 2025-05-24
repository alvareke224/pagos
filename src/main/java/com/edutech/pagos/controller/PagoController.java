package com.edutech.pagos.controller;

import com.edutech.pagos.model.Pago;
import com.edutech.pagos.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

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
    public Pago guardar(@Valid @RequestBody Pago pago) {
        return servicio.guardar(pago);
    }
    

    @GetMapping("/{id}")
    public Pago buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }

    
}
