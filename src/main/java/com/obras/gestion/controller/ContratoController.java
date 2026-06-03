package com.obras.gestion.controller;

import com.obras.gestion.model.Contrato;
import com.obras.gestion.model.EstadoContrato;
import com.obras.gestion.model.TipoContrato;
import com.obras.gestion.service.ContratoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@CrossOrigin(origins = "*")
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @PostMapping
    public ResponseEntity<Contrato> crear(@RequestBody Contrato contrato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoService.crear(contrato));
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> obtenerTodos() {
        return ResponseEntity.ok(contratoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(contratoService.obtenerPorId(id));
    }

    @GetMapping("/obra/{obraId}")
    public ResponseEntity<List<Contrato>> obtenerPorObra(@PathVariable String obraId) {
        return ResponseEntity.ok(contratoService.obtenerPorObra(obraId));
    }

    @GetMapping("/contratista/{contratistaId}")
    public ResponseEntity<List<Contrato>> obtenerPorContratista(@PathVariable String contratistaId) {
        return ResponseEntity.ok(contratoService.obtenerPorContratista(contratistaId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Contrato>> obtenerPorEstado(@PathVariable EstadoContrato estado) {
        return ResponseEntity.ok(contratoService.obtenerPorEstado(estado));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Contrato>> obtenerPorTipo(@PathVariable TipoContrato tipo) {
        return ResponseEntity.ok(contratoService.obtenerPorTipo(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> actualizar(@PathVariable String id, @RequestBody Contrato contrato) {
        return ResponseEntity.ok(contratoService.actualizar(id, contrato));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Contrato> cancelar(@PathVariable String id) {
        return ResponseEntity.ok(contratoService.cancelar(id));
    }

    @PostMapping("/verificar-vigencia")
    public ResponseEntity<Void> verificarVigencia() {
        contratoService.verificarVigenciaTodos();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        contratoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
