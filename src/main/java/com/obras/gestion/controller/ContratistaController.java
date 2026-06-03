package com.obras.gestion.controller;

import com.obras.gestion.model.Contratista;
import com.obras.gestion.service.ContratistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contratistas")
@CrossOrigin(origins = "*")
public class ContratistaController {

    private final ContratistaService contratistaService;

    public ContratistaController(ContratistaService contratistaService) {
        this.contratistaService = contratistaService;
    }

    @PostMapping
    public ResponseEntity<Contratista> registrar(@RequestBody Contratista contratista) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratistaService.registrar(contratista));
    }

    @GetMapping
    public ResponseEntity<List<Contratista>> obtenerTodos() {
        return ResponseEntity.ok(contratistaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contratista> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(contratistaService.obtenerPorId(id));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Contratista>> obtenerActivos() {
        return ResponseEntity.ok(contratistaService.obtenerActivos());
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Contratista>> obtenerPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(contratistaService.obtenerPorEspecialidad(especialidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contratista> actualizar(@PathVariable String id,
                                                   @RequestBody Contratista contratista) {
        return ResponseEntity.ok(contratistaService.actualizar(id, contratista));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Contratista> desactivar(@PathVariable String id) {
        return ResponseEntity.ok(contratistaService.desactivar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        contratistaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
