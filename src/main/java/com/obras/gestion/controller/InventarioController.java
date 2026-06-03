package com.obras.gestion.controller;

import com.obras.gestion.model.Inventario;
import com.obras.gestion.model.Material;
import com.obras.gestion.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
@CrossOrigin(origins = "*")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/obra/{obraId}")
    public ResponseEntity<Inventario> obtenerPorObra(@PathVariable String obraId) {
        return ResponseEntity.ok(inventarioService.obtenerPorObra(obraId));
    }

    @PostMapping("/obra/{obraId}/materiales")
    public ResponseEntity<Inventario> agregarMaterial(@PathVariable String obraId,
                                                       @RequestBody Material material) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventarioService.agregarMaterial(obraId, material));
    }

    @PatchMapping("/obra/{obraId}/materiales/{materialId}/stock")
    public ResponseEntity<Inventario> actualizarStock(@PathVariable String obraId,
                                                       @PathVariable String materialId,
                                                       @RequestParam int cantidad) {
        return ResponseEntity.ok(inventarioService.actualizarStock(obraId, materialId, cantidad));
    }

    @GetMapping("/obra/{obraId}/stock-bajo")
    public ResponseEntity<List<Material>> obtenerStockBajo(@PathVariable String obraId) {
        return ResponseEntity.ok(inventarioService.obtenerMaterialesStockBajo(obraId));
    }

    @DeleteMapping("/obra/{obraId}/materiales/{materialId}")
    public ResponseEntity<Inventario> eliminarMaterial(@PathVariable String obraId,
                                                        @PathVariable String materialId) {
        return ResponseEntity.ok(inventarioService.eliminarMaterial(obraId, materialId));
    }
}
