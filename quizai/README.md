# QuizAI API

API REST para generación automática de quizzes con Inteligencia Artificial. El usuario ingresa un tema o texto, indica la cantidad de preguntas y el nivel de dificultad, y la IA genera un quiz completo. Al responder, el sistema califica automáticamente y guarda el historial de resultados.

**Stack:** Java 21 · Spring Boot 3.3 · MongoDB · Groq (Llama 3.3) · JWT · Docker

---

## Requisitos

- Docker Desktop instalado y corriendo

---

## Configuración

1. Copia el archivo de variables de entorno:
   ```bash
   cp .env.example .env
   ```

2. Edita el `.env` con tus valores:
   ```
   MONGODB_URI=mongodb://mongodb:27017
   MONGODB_DATABASE=quizai_db
   JWT_SECRET=una_clave_secreta_larga_de_al_menos_32_caracteres
   JWT_EXPIRATION_MS=86400000
   GROQ_API_KEY=gsk_tu_api_key_aqui
   GROQ_MODEL=llama-3.3-70b-versatile
   GROQ_MAX_TOKENS=8192
   PORT=8080
   ```

   > Obtén tu API key gratuita de Groq en [console.groq.com/keys](https://console.groq.com/keys)

---

## Correr la aplicación

```bash
docker-compose up --build
```

La primera vez tarda unos minutos mientras descarga las imágenes y compila el proyecto.

| Servicio | URL |
|----------|-----|
| API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| MongoDB | mongodb://localhost:27017 |

Para detener:
```bash
docker-compose down
```

---

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/auth/registro` | Registrar usuario |
| POST | `/auth/login` | Iniciar sesión |
| POST | `/quizzes/generar` | Generar quiz desde tema |
| POST | `/quizzes/generar/texto` | Generar quiz desde texto |
| POST | `/quizzes/{id}/resultados` | Enviar respuestas |
| GET | `/quizzes/{id}/resultados` | Ver resultado |
| GET | `/quizzes/{id}/preguntas/{preguntaId}/explicacion` | Explicación con IA |
| GET | `/usuarios/me/estadisticas` | Estadísticas del usuario |

---

## Diagramas
### Diagrama de contexto
<img width="1747" height="745" alt="image" src="https://github.com/user-attachments/assets/21ed6210-8f5a-49e1-b5af-522e69cd58a8" />


### Diagrama de Clases
<img width="1516" height="1085" alt="image" src="https://github.com/user-attachments/assets/cb3d12b2-dd7a-4653-b9a3-e48411595971" />



### Diagrama de Vista de Desarrollo

<img width="1041" height="1107" alt="image" src="https://github.com/user-attachments/assets/80086f68-8ef8-4013-96bb-53747a6d3c39" />


### Diagrama de Componentes

<img width="1792" height="730" alt="image" src="https://github.com/user-attachments/assets/a8c1d403-3b43-4448-ad7a-cd3eec4bcef3" />


### Diagrama de Despliegue



