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

    public Pago guardar(Pago pago) {
        if (repositorio.existsByUsuarioAndFechaAndMonto(
                pago.getUsuario(),
                pago.getFecha(),
                pago.getMonto())) {
            throw new IllegalArgumentException("Este pago ya ha sido registrado.");
        }

        double montoConDescuento = calcularMontoConDescuento(pago.getMonto(), pago.getCodigoCupon());
        pago.setMontoFinal(montoConDescuento);

        return repositorio.save(pago);
    }

    public Pago buscarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    public Pago actualizar(Long id, Pago nuevosDatos) {
        Pago pagoExistente = buscarPorId(id);
        if (pagoExistente == null) return null;

        pagoExistente.setUsuario(nuevosDatos.getUsuario());
        pagoExistente.setMonto(nuevosDatos.getMonto());
        pagoExistente.setMetodo(nuevosDatos.getMetodo());
        pagoExistente.setFecha(nuevosDatos.getFecha());
        pagoExistente.setCodigoCupon(nuevosDatos.getCodigoCupon());
        pagoExistente.setMontoFinal(calcularMontoConDescuento(nuevosDatos.getMonto(), nuevosDatos.getCodigoCupon()));

        return repositorio.save(pagoExistente);
    }

    private double calcularMontoConDescuento(double monto, String codigoCupon) {
        if (codigoCupon == null) return monto;

        return switch (codigoCupon.toUpperCase()) {
            case "EDU10" -> monto * 0.90;
            case "DESC20" -> monto * 0.80;
            case "FULL30" -> monto * 0.70;
            default -> monto;
        };
    }
}
