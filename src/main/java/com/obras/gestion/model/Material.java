package com.obras.gestion.model;

public class Material {

    private String id;
    private String nombre;
    private int cantidad;
    private String unidad;
    private int stockMinimo;
    private double precioUnitario;

    public Material() {}

    public Material(String id, String nombre, int cantidad, String unidad,
                    int stockMinimo, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.stockMinimo = stockMinimo;
        this.precioUnitario = precioUnitario;
    }

    public boolean tieneStockBajo() {
        return this.cantidad <= this.stockMinimo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
}
