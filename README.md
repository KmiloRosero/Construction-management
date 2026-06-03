# Sistema de Gestión de Obras de Construcción
## Backend - Spring Boot + MongoDB + Gradle

---

## Requisitos
- Java 17+
- MongoDB corriendo en localhost:27017
- Gradle (o usar el wrapper incluido)

## Cómo correr

```bash
./gradlew bootRun
```

O en Windows:
```bash
gradlew.bat bootRun
```

El servidor inicia en: http://localhost:8080

---

## Endpoints disponibles

### OBRAS
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/obras | Crear obra |
| GET | /api/obras | Listar todas |
| GET | /api/obras/{id} | Obtener por ID |
| GET | /api/obras/estado/{estado} | Filtrar por estado |
| PUT | /api/obras/{id} | Actualizar obra |
| PATCH | /api/obras/{id}/estado?estado=EN_EJECUCION | Cambiar estado |
| PATCH | /api/obras/{obraId}/contratistas/{contratistaId} | Asignar contratista |
| DELETE | /api/obras/{id} | Eliminar obra |

### CONTRATISTAS
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/contratistas | Registrar contratista |
| GET | /api/contratistas | Listar todos |
| GET | /api/contratistas/{id} | Obtener por ID |
| GET | /api/contratistas/activos | Solo activos |
| GET | /api/contratistas/especialidad/{esp} | Filtrar por especialidad |
| PUT | /api/contratistas/{id} | Actualizar |
| PATCH | /api/contratistas/{id}/desactivar | Desactivar |
| DELETE | /api/contratistas/{id} | Eliminar |

### CONTRATOS
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/contratos | Crear contrato |
| GET | /api/contratos | Listar todos |
| GET | /api/contratos/{id} | Obtener por ID |
| GET | /api/contratos/obra/{obraId} | Por obra |
| GET | /api/contratos/contratista/{id} | Por contratista |
| GET | /api/contratos/estado/{estado} | Por estado |
| GET | /api/contratos/tipo/{tipo} | Por tipo |
| PUT | /api/contratos/{id} | Actualizar |
| PATCH | /api/contratos/{id}/cancelar | Cancelar |
| POST | /api/contratos/verificar-vigencia | Verificar todos |
| DELETE | /api/contratos/{id} | Eliminar |

### INVENTARIO
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/inventarios/obra/{obraId} | Ver inventario de obra |
| POST | /api/inventarios/obra/{obraId}/materiales | Agregar material |
| PATCH | /api/inventarios/obra/{obraId}/materiales/{matId}/stock?cantidad=50 | Actualizar stock |
| GET | /api/inventarios/obra/{obraId}/stock-bajo | Ver materiales con stock bajo |
| DELETE | /api/inventarios/obra/{obraId}/materiales/{matId} | Eliminar material |

---

## Ejemplos de JSON

### Crear Obra
```json
{
  "nombre": "Torre Empresarial Norte",
  "ubicacion": "Calle 100 #15-30, Bogotá",
  "fechaInicio": "2024-01-15",
  "fechaFin": "2025-06-30",
  "presupuesto": 5000000000
}
```

### Crear Contratista
```json
{
  "nombre": "Carlos Pérez",
  "email": "carlos@constructora.com",
  "telefono": "3001234567",
  "especialidad": "Estructuras Metálicas",
  "licencia": "LIC-2024-001"
}
```

### Crear Contrato de Obra
```json
{
  "_class": "com.obras.gestion.model.ContratoObra",
  "tipo": "OBRA",
  "fechaInicio": "2024-01-15",
  "fechaFin": "2024-12-31",
  "valor": 800000000,
  "obraId": "<id-de-la-obra>",
  "contratistaId": "<id-del-contratista>",
  "metrosCuadrados": 2500.0
}
```

### Crear Contrato de Servicio
```json
{
  "_class": "com.obras.gestion.model.ContratoServicio",
  "tipo": "SERVICIO",
  "fechaInicio": "2024-02-01",
  "fechaFin": "2024-11-30",
  "valor": 120000000,
  "obraId": "<id-de-la-obra>",
  "contratistaId": "<id-del-contratista>",
  "tipoServicio": "Instalaciones Eléctricas"
}
```

### Agregar Material
```json
{
  "nombre": "Cemento Gris 50kg",
  "cantidad": 500,
  "unidad": "bultos",
  "stockMinimo": 50,
  "precioUnitario": 28000
}
```

---

## Estados válidos

**EstadoObra:** PLANIFICACION | EN_EJECUCION | PAUSADA | FINALIZADA

**EstadoContrato:** ACTIVO | VENCIDO | CANCELADO

**TipoContrato:** OBRA | SERVICIO
