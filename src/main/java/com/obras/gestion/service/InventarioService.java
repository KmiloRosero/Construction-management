package com.obras.gestion.service;

import com.obras.gestion.exception.ResourceNotFoundException;
import com.obras.gestion.model.Inventario;
import com.obras.gestion.model.Material;
import com.obras.gestion.repository.InventarioRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final NotificacionService notificacionService;

    public InventarioService(InventarioRepository inventarioRepository,
                             @Lazy NotificacionService notificacionService) {
        this.inventarioRepository = inventarioRepository;
        this.notificacionService = notificacionService;
    }

    public Inventario crearInventarioParaObra(String obraId) {
        Inventario inventario = new Inventario(obraId);
        return inventarioRepository.save(inventario);
    }

    public Inventario obtenerPorObra(String obraId) {
        return inventarioRepository.findByObraId(obraId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado para obra: " + obraId));
    }

    public Inventario obtenerPorId(String id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con id: " + id));
    }

    public Inventario agregarMaterial(String obraId, Material material) {
        Inventario inventario = obtenerPorObra(obraId);
        material.setId(UUID.randomUUID().toString());
        inventario.agregarMaterial(material);
        Inventario guardado = inventarioRepository.save(inventario);
        if (material.tieneStockBajo()) {
            notificacionService.alertarStockBajo(material, obraId);
        }
        return guardado;
    }

    public Inventario actualizarStock(String obraId, String materialId, int nuevaCantidad) {
        Inventario inventario = obtenerPorObra(obraId);
        inventario.actualizarStock(materialId, nuevaCantidad);
        Inventario guardado = inventarioRepository.save(inventario);
        inventario.getMateriales().stream()
                .filter(m -> m.getId().equals(materialId))
                .findFirst()
                .ifPresent(m -> {
                    if (m.tieneStockBajo()) {
                        notificacionService.alertarStockBajo(m, obraId);
                    }
                });
        return guardado;
    }

    public List<Material> obtenerMaterialesStockBajo(String obraId) {
        Inventario inventario = obtenerPorObra(obraId);
        return inventario.getMaterialesConStockBajo();
    }

    public Inventario eliminarMaterial(String obraId, String materialId) {
        Inventario inventario = obtenerPorObra(obraId);
        inventario.eliminarMaterial(materialId);
        return inventarioRepository.save(inventario);
    }
}
