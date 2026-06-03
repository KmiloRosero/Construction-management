package com.obras.gestion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contratistas")
public class Contratista extends Persona {

    @Id
    private String id;
    private String especialidad;
    private String licencia;
    private boolean activo;

    public Contratista() {}

    public Contratista(String id, String nombre, String email, String telefono,
                       String especialidad, String licencia) {
        super(id, nombre, email, telefono);
        this.id = id;
        this.especialidad = especialidad;
        this.licencia = licencia;
        this.activo = true;
    }

    @Override
    public String getId() { return id; }
    @Override
    public void setId(String id) {
        super.setId(id);
        this.id = id;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
