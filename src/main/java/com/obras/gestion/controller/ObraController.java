package com.obras.gestion.controller;

import com.obras.gestion.model.Obra;
import com.obras.gestion.model.EstadoObra;
import com.obras.gestion.service.ObraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/obras")
@CrossOrigin(origins = "*")
public class ObraController {

    private final ObraService obraService;

    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @PostMapping
    public ResponseEntity<Obra> crear(@RequestBody Obra obra) {
        return ResponseEntity.status(HttpStatus.CREATED).body(obraService.crearObra(obra));
    }

    @GetMapping
    public ResponseEntity<List<Obra>> obtenerTodas() {
        return ResponseEntity.ok(obraService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(obraService.obtenerPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Obra>> obtenerPorEstado(@PathVariable EstadoObra estado) {
        return ResponseEntity.ok(obraService.obtenerPorEstado(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> actualizar(@PathVariable String id, @RequestBody Obra obra) {
        return ResponseEntity.ok(obraService.actualizarObra(id, obra));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Obra> actualizarEstado(@PathVariable String id,
                                                  @RequestParam EstadoObra estado) {
        return ResponseEntity.ok(obraService.actualizarEstado(id, estado));
    }

    @PatchMapping("/{obraId}/contratistas/{contratistaId}")
    public ResponseEntity<Obra> asignarContratista(@PathVariable String obraId,
                                                    @PathVariable String contratistaId) {
        return ResponseEntity.ok(obraService.asignarContratista(obraId, contratistaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        obraService.eliminarObra(id);
        return ResponseEntity.noContent().build();
    }
}
