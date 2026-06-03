package com.obras.gestion.service;

import com.obras.gestion.exception.ResourceNotFoundException;
import com.obras.gestion.model.Contratista;
import com.obras.gestion.repository.ContratistaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContratistaService {

    private final ContratistaRepository contratistaRepository;

    public ContratistaService(ContratistaRepository contratistaRepository) {
        this.contratistaRepository = contratistaRepository;
    }

    public Contratista registrar(Contratista contratista) {
        contratista.setActivo(true);
        return contratistaRepository.save(contratista);
    }

    public List<Contratista> obtenerTodos() {
        return contratistaRepository.findAll();
    }

    public Contratista obtenerPorId(String id) {
        return contratistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contratista no encontrado con id: " + id));
    }

    public List<Contratista> obtenerActivos() {
        return contratistaRepository.findByActivo(true);
    }

    public List<Contratista> obtenerPorEspecialidad(String especialidad) {
        return contratistaRepository.findByEspecialidadIgnoreCase(especialidad);
    }

    public Contratista actualizar(String id, Contratista datosActualizados) {
        Contratista contratista = obtenerPorId(id);
        contratista.setNombre(datosActualizados.getNombre());
        contratista.setEmail(datosActualizados.getEmail());
        contratista.setTelefono(datosActualizados.getTelefono());
        contratista.setEspecialidad(datosActualizados.getEspecialidad());
        contratista.setLicencia(datosActualizados.getLicencia());
        return contratistaRepository.save(contratista);
    }

    public Contratista desactivar(String id) {
        Contratista contratista = obtenerPorId(id);
        contratista.setActivo(false);
        return contratistaRepository.save(contratista);
    }

    public void eliminar(String id) {
        obtenerPorId(id);
        contratistaRepository.deleteById(id);
    }
}
