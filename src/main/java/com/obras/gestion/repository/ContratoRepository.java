package com.obras.gestion.repository;

import com.obras.gestion.model.Contrato;
import com.obras.gestion.model.EstadoContrato;
import com.obras.gestion.model.TipoContrato;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContratoRepository extends MongoRepository<Contrato, String> {
    List<Contrato> findByObraId(String obraId);
    List<Contrato> findByContratistaId(String contratistaId);
    List<Contrato> findByEstado(EstadoContrato estado);
    List<Contrato> findByTipo(TipoContrato tipo);
}
