package com.obras.gestion.service;

import com.obras.gestion.exception.ResourceNotFoundException;
import com.obras.gestion.model.Obra;
import com.obras.gestion.model.EstadoObra;
import com.obras.gestion.repository.ObraRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ObraService {

    private final ObraRepository obraRepository;
    private final InventarioService inventarioService;

    public ObraService(ObraRepository obraRepository, InventarioService inventarioService) {
        this.obraRepository = obraRepository;
        this.inventarioService = inventarioService;
    }

    public Obra crearObra(Obra obra) {
        obra.setEstado(EstadoObra.PLANIFICACION);
        Obra obraGuardada = obraRepository.save(obra);
        inventarioService.crearInventarioParaObra(obraGuardada.getId());
        return obraGuardada;
    }

    public List<Obra> obtenerTodas() {
        return obraRepository.findAll();
    }

    public Obra obtenerPorId(String id) {
        return obraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Obra no encontrada con id: " + id));
    }

    public List<Obra> obtenerPorEstado(EstadoObra estado) {
        return obraRepository.findByEstado(estado);
    }

    public Obra actualizarObra(String id, Obra obraActualizada) {
        Obra obra = obtenerPorId(id);
        obra.setNombre(obraActualizada.getNombre());
        obra.setUbicacion(obraActualizada.getUbicacion());
        obra.setFechaInicio(obraActualizada.getFechaInicio());
        obra.setFechaFin(obraActualizada.getFechaFin());
        obra.setPresupuesto(obraActualizada.getPresupuesto());
        return obraRepository.save(obra);
    }

    public Obra actualizarEstado(String id, EstadoObra nuevoEstado) {
        Obra obra = obtenerPorId(id);
        obra.setEstado(nuevoEstado);
        return obraRepository.save(obra);
    }

    public Obra asignarContratista(String obraId, String contratistaId) {
        Obra obra = obtenerPorId(obraId);
        if (!obra.getContratistaIds().contains(contratistaId)) {
            obra.getContratistaIds().add(contratistaId);
        }
        return obraRepository.save(obra);
    }

    public void eliminarObra(String id) {
        obtenerPorId(id);
        obraRepository.deleteById(id);
    }
}
