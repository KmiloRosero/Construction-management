package com.obras.gestion.repository;

import com.obras.gestion.model.Contratista;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContratistaRepository extends MongoRepository<Contratista, String> {
    List<Contratista> findByActivo(boolean activo);
    List<Contratista> findByEspecialidadIgnoreCase(String especialidad);
    Optional<Contratista> findByLicencia(String licencia);
}
