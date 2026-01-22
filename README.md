# API para la Gestión de Solicitudes de Préstamos Personales

Proyecto API REST desarrollado con Spring Boot para la gestión de solicitudes de préstamos personales. Empleando una arquitectura hexagonal, lo que garantiza un código desacoplado, robusto y de fácil mantenimiento.

## Tecnologías
* Java 17: versión de soporte extendido para garantizar estabilidad.
* Spring Boot 3.4.1: empleo de la versión más reciente para aprovechar sus mejoras
* Maven 3.9.12: gestor de dependencias óptimo para la versión de java utilizada

## Arquitectura
El proyecto está realizado siguiendo la arquitectura hexagonal:
* Domain: Modelo de negocio
* Application: Servicio que orquesta la lógica de negocio
* Infrastructure: Controladores de endpoints y persistencia

## Funcionalidad
La API se puede probar a través de Swagger UI una vez arrancado el servicio (http://localhost:8080/swagger-ui/index.html)
* `POST /prestamos`: Creación de una nueva solicitud de préstamo.
* `GET /prestamos/{id}`: Obtener una solicitud de préstamos por identificador de solicitud.
* `GET /prestamos`: Obtener todas las solicitudes de préstamo.
* `PATCH /prestamos/{id}/estado`: Modificar estado de una solicitud de préstamo.

## Mejoras
* Técnicas:
  - Persistencia de datos real en una base datos relacional empleando Spring Data JPA.
  - Securizar la aplicación con Spring Security.
* Funcional:
  - Búsquedas por más filtros como nombre o documento de identificación.
  - Histórico de cambios de estado de una solicitud.

