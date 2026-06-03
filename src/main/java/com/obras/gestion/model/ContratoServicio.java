package com.obras.gestion.model;

import java.time.LocalDate;

public class ContratoServicio extends Contrato {

    private String tipoServicio;

    public ContratoServicio() {
        super();
        setTipo(TipoContrato.SERVICIO);
    }

    public ContratoServicio(LocalDate fechaInicio, LocalDate fechaFin, double valor,
                            String obraId, String contratistaId, String tipoServicio) {
        super(TipoContrato.SERVICIO, fechaInicio, fechaFin, valor, obraId, contratistaId);
        this.tipoServicio = tipoServicio;
    }

    @Override
    public String getDescripcionTipo() {
        return "Contrato de Servicio - " + tipoServicio;
    }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
}
