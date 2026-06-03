package com.obras.gestion.service;

import com.obras.gestion.model.Contrato;
import com.obras.gestion.model.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    private static final Logger logger = LoggerFactory.getLogger(NotificacionService.class);

    public void alertarStockBajo(Material material, String obraId) {
        String mensaje = String.format(
            "[ALERTA STOCK] Material '%s' en obra '%s' tiene stock bajo. Cantidad actual: %d (mínimo: %d)",
            material.getNombre(), obraId, material.getCantidad(), material.getStockMinimo()
        );
        logger.warn(mensaje);
        // Aquí puedes integrar envío de email real con JavaMailSender
    }

    public void alertarContratoVencido(Contrato contrato) {
        String mensaje = String.format(
            "[ALERTA CONTRATO] El contrato '%s' de tipo '%s' ha vencido o fue cancelado.",
            contrato.getId(), contrato.getTipo()
        );
        logger.warn(mensaje);
        // Aquí puedes integrar envío de email real con JavaMailSender
    }

    public void notificar(String destinatario, String asunto, String cuerpo) {
        logger.info("[NOTIFICACION] Para: {} | Asunto: {} | Mensaje: {}", destinatario, asunto, cuerpo);
    }
}
