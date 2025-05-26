package com.edutech.pagos.service;

import com.edutech.pagos.model.Pago;
import com.edutech.pagos.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository repositorio;

    public List<Pago> listar() {
        return repositorio.findAll();
    }

    public Pago guardar(Pago p) {
        p.setMontoFinal(calcularMontoConDescuento(p.getMonto(), p.getCodigoCupon()));
        return repositorio.save(p);
    }

    public Pago buscarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    private double calcularMontoConDescuento(double monto, String codigoCupon) {
        if (codigoCupon == null) return monto;

        return switch (codigoCupon.toUpperCase()) {
            case "EDU10" -> monto * 0.90; // 10% descuento
            case "DESC20" -> monto * 0.80; // 20% descuento
            case "FULL30" -> monto * 0.70; // 30% descuento
            default -> monto; // cupón no válido → sin descuento
        };
    }
}
