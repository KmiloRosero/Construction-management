package com.obras.gestion.model;

import java.time.LocalDate;

public class ContratoObra extends Contrato {

    private double metrosCuadrados;

    public ContratoObra() {
        super();
        setTipo(TipoContrato.OBRA);
    }

    public ContratoObra(LocalDate fechaInicio, LocalDate fechaFin, double valor,
                        String obraId, String contratistaId, double metrosCuadrados) {
        super(TipoContrato.OBRA, fechaInicio, fechaFin, valor, obraId, contratistaId);
        this.metrosCuadrados = metrosCuadrados;
    }

    @Override
    public String getDescripcionTipo() {
        return "Contrato de Obra - " + metrosCuadrados + " m²";
    }

    public double getMetrosCuadrados() { return metrosCuadrados; }
    public void setMetrosCuadrados(double metrosCuadrados) { this.metrosCuadrados = metrosCuadrados; }
}
