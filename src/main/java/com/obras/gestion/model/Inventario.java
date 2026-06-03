package com.obras.gestion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "inventarios")
public class Inventario {

    @Id
    private String id;
    private String obraId;
    private List<Material> materiales;

    public Inventario() {
        this.materiales = new ArrayList<>();
    }

    public Inventario(String obraId) {
        this.obraId = obraId;
        this.materiales = new ArrayList<>();
    }

    public void agregarMaterial(Material material) {
        if (material.getId() == null) {
            material.setId(UUID.randomUUID().toString());
        }
        this.materiales.add(material);
    }

    public void actualizarStock(String materialId, int nuevaCantidad) {
        materiales.stream()
                .filter(m -> m.getId().equals(materialId))
                .findFirst()
                .ifPresent(m -> m.setCantidad(nuevaCantidad));
    }

    public List<Material> getMaterialesConStockBajo() {
        return materiales.stream()
                .filter(Material::tieneStockBajo)
                .toList();
    }

    public void eliminarMaterial(String materialId) {
        materiales.removeIf(m -> m.getId().equals(materialId));
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getObraId() { return obraId; }
    public void setObraId(String obraId) { this.obraId = obraId; }

    public List<Material> getMateriales() { return materiales; }
    public void setMateriales(List<Material> materiales) { this.materiales = materiales; }
}
