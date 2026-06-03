package com.obras.gestion.repository;

import com.obras.gestion.model.Obra;
import com.obras.gestion.model.EstadoObra;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObraRepository extends MongoRepository<Obra, String> {
    List<Obra> findByEstado(EstadoObra estado);
    List<Obra> findByNombreContainingIgnoreCase(String nombre);
    List<Obra> findByContratistaIdsContaining(String contratistaId);
}
