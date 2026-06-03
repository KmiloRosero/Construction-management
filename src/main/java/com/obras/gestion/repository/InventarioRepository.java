package com.obras.gestion.repository;

import com.obras.gestion.model.Inventario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InventarioRepository extends MongoRepository<Inventario, String> {
    Optional<Inventario> findByObraId(String obraId);
}
