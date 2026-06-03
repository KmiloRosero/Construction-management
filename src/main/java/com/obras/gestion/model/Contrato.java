package com.obras.gestion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "contratos")
public abstract class Contrato {

    @Id
    private String id;
    private TipoContrato tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double valor;
    private EstadoContrato estado;
    private String obraId;
    private String contratistaId;

    public Contrato() {
        this.estado = EstadoContrato.ACTIVO;
    }

    public Contrato(TipoContrato tipo, LocalDate fechaInicio, LocalDate fechaFin,
                    double valor, String obraId, String contratistaId) {
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.valor = valor;
        this.obraId = obraId;
        this.contratistaId = contratistaId;
        this.estado = EstadoContrato.ACTIVO;
    }

    public abstract String getDescripcionTipo();

    public void verificarVigencia() {
        if (LocalDate.now().isAfter(this.fechaFin)) {
            this.estado = EstadoContrato.VENCIDO;
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public TipoContrato getTipo() { return tipo; }
    public void setTipo(TipoContrato tipo) { this.tipo = tipo; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public EstadoContrato getEstado() { return estado; }
    public void setEstado(EstadoContrato estado) { this.estado = estado; }

    public String getObraId() { return obraId; }
    public void setObraId(String obraId) { this.obraId = obraId; }

    public String getContratistaId() { return contratistaId; }
    public void setContratistaId(String contratistaId) { this.contratistaId = contratistaId; }
}
