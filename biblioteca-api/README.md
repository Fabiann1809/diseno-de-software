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
- Conexión a MongoDB Atlas

## Configuración local

Las credenciales de MongoDB no se incluyen en el repositorio. Crea el archivo:

```
src/main/resources/application-local.properties
```

Con el siguiente contenido:

```properties
spring.data.mongodb.uri=mongodb+srv://<usuario>:<contraseña>@<cluster>.mongodb.net/biblioteca_db?retryWrites=true&w=majority&appName=Cluster0
```

## Ejecutar el proyecto

```powershell
./run.ps1
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
