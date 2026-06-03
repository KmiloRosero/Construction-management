package com.obras.gestion.service;

import com.obras.gestion.exception.ResourceNotFoundException;
import com.obras.gestion.model.Contrato;
import com.obras.gestion.model.EstadoContrato;
import com.obras.gestion.model.TipoContrato;
import com.obras.gestion.repository.ContratoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final NotificacionService notificacionService;

    public ContratoService(ContratoRepository contratoRepository,
                           NotificacionService notificacionService) {
        this.contratoRepository = contratoRepository;
        this.notificacionService = notificacionService;
    }

    public Contrato crear(Contrato contrato) {
        contrato.setEstado(EstadoContrato.ACTIVO);
        return contratoRepository.save(contrato);
    }

    public List<Contrato> obtenerTodos() {
        return contratoRepository.findAll();
    }

    public Contrato obtenerPorId(String id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con id: " + id));
    }

    public List<Contrato> obtenerPorObra(String obraId) {
        return contratoRepository.findByObraId(obraId);
    }

    public List<Contrato> obtenerPorContratista(String contratistaId) {
        return contratoRepository.findByContratistaId(contratistaId);
    }

    public List<Contrato> obtenerPorEstado(EstadoContrato estado) {
        return contratoRepository.findByEstado(estado);
    }

    public List<Contrato> obtenerPorTipo(TipoContrato tipo) {
        return contratoRepository.findByTipo(tipo);
    }

    public Contrato actualizar(String id, Contrato datosActualizados) {
        Contrato contrato = obtenerPorId(id);
        contrato.setFechaInicio(datosActualizados.getFechaInicio());
        contrato.setFechaFin(datosActualizados.getFechaFin());
        contrato.setValor(datosActualizados.getValor());
        return contratoRepository.save(contrato);
    }

    public Contrato cancelar(String id) {
        Contrato contrato = obtenerPorId(id);
        contrato.setEstado(EstadoContrato.CANCELADO);
        notificacionService.alertarContratoVencido(contrato);
        return contratoRepository.save(contrato);
    }

    public void verificarVigenciaTodos() {
        List<Contrato> activos = contratoRepository.findByEstado(EstadoContrato.ACTIVO);
        activos.forEach(c -> {
            c.verificarVigencia();
            if (c.getEstado() == EstadoContrato.VENCIDO) {
                notificacionService.alertarContratoVencido(c);
            }
            contratoRepository.save(c);
        });
    }

    public void eliminar(String id) {
        obtenerPorId(id);
        contratoRepository.deleteById(id);
    }
}
