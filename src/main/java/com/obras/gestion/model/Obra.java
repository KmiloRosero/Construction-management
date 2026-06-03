package com.obras.gestion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "obras")
public class Obra {

    @Id
    private String id;
    private String nombre;
    private String ubicacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoObra estado;
    private double presupuesto;
    private List<String> contratistaIds;

    public Obra() {
        this.contratistaIds = new ArrayList<>();
        this.estado = EstadoObra.PLANIFICACION;
    }

    public Obra(String nombre, String ubicacion, LocalDate fechaInicio,
                LocalDate fechaFin, double presupuesto) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.presupuesto = presupuesto;
        this.estado = EstadoObra.PLANIFICACION;
        this.contratistaIds = new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public EstadoObra getEstado() { return estado; }
    public void setEstado(EstadoObra estado) { this.estado = estado; }

    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }

    public List<String> getContratistaIds() { return contratistaIds; }
    public void setContratistaIds(List<String> contratistaIds) { this.contratistaIds = contratistaIds; }
}
