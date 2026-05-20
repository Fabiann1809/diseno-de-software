# Biblioteca API

REST API para la gestión de una biblioteca, desarrollada con Spring Boot y MongoDB.

## Tecnologías

- Java 17
- Spring Boot 3.5
- Spring Data MongoDB
- Lombok
- Maven

## Requisitos

- Java 17+
- Maven 3.x
- Conexión a MongoDB Atlas (ya configurada en `application.properties`)

## Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8080`.

## Recursos disponibles

| Recurso       | Ruta base         |
|---------------|-------------------|
| Libros        | `/libros`         |
| Ejemplares    | `/ejemplares`     |
| Préstamos     | `/prestamos`      |
| Usuarios      | `/usuarios`       |
| Bibliotecarios| `/bibliotecarios` |

## Modelos principales

- **Libro** — información bibliográfica
- **Ejemplar** — copia física de un libro
- **Prestamo** — registro de préstamo de un ejemplar
- **Usuario** — puede ser `Estudiante` o `Profesor`
- **Bibliotecario** — gestiona el sistema
