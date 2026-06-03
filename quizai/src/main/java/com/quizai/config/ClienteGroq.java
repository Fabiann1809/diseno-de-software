package com.quizai.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizai.exception.ServicioIAException;
import com.quizai.model.Dificultad;
import com.quizai.model.Opcion;
import com.quizai.model.Pregunta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClienteGroq {

    private static final Logger log = LoggerFactory.getLogger(ClienteGroq.class);

    private final String apiUrl;
    private final String apiKey;
    private final String modelo;
    private final int maxTokens;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ClienteGroq(
            @Value("${app.groq.api-url}") String apiUrl,
            @Value("${app.groq.api-key}") String apiKey,
            @Value("${app.groq.model}") String modelo,
            @Value("${app.groq.max-tokens}") int maxTokens) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.modelo = modelo;
        this.maxTokens = maxTokens;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String enviarPrompt(String promptSistema, String promptUsuario) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("model", modelo);
        cuerpo.put("max_tokens", maxTokens);
        cuerpo.put("messages", List.of(
                Map.of("role", "system", "content", promptSistema),
                Map.of("role", "user", "content", promptUsuario)
        ));

        HttpHeaders encabezados = new HttpHeaders();
        encabezados.setContentType(MediaType.APPLICATION_JSON);
        encabezados.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> solicitud = new HttpEntity<>(cuerpo, encabezados);

        try {
            ResponseEntity<String> respuesta = restTemplate.postForEntity(apiUrl, solicitud, String.class);
            JsonNode raiz = objectMapper.readTree(respuesta.getBody());
            return raiz.path("choices").get(0).path("message").path("content").asText();
        } catch (RestClientException e) {
            log.error("Error al contactar Groq API: {}", e.getMessage());
            throw new ServicioIAException("No se pudo conectar con el servicio de IA", e);
        } catch (Exception e) {
            log.error("Error procesando respuesta de Groq: {}", e.getMessage());
            throw new ServicioIAException("Error procesando la respuesta de la IA", e);
        }
    }

    public String construirPromptGeneracion(String tema, Dificultad dificultad, int cantidad) {
        return String.format(
                "Genera exactamente %d preguntas de opción múltiple sobre el tema: '%s'. " +
                "Nivel de dificultad: %s. " +
                "Cada pregunta debe tener exactamente 4 opciones (A, B, C, D). " +
                "Responde ÚNICAMENTE con un JSON válido con esta estructura exacta, sin texto adicional:\n" +
                """
                {
                  "preguntas": [
                    {
                      "textoPregunta": "¿Pregunta aquí?",
                      "opciones": [
                        {"etiqueta": "A", "texto": "Opción A"},
                        {"etiqueta": "B", "texto": "Opción B"},
                        {"etiqueta": "C", "texto": "Opción C"},
                        {"etiqueta": "D", "texto": "Opción D"}
                      ],
                      "etiquetaCorrecta": "A"
                    }
                  ]
                }
                """,
                cantidad, tema, dificultad.name()
        );
    }

    public String construirPromptDesdeTexto(String texto, Dificultad dificultad, int cantidad) {
        return String.format(
                "Basándote en el siguiente texto, genera exactamente %d preguntas de opción múltiple. " +
                "Nivel de dificultad: %s. " +
                "Cada pregunta debe tener exactamente 4 opciones (A, B, C, D). " +
                "Responde ÚNICAMENTE con un JSON válido con esta estructura exacta, sin texto adicional:\n" +
                """
                {
                  "preguntas": [
                    {
                      "textoPregunta": "¿Pregunta aquí?",
                      "opciones": [
                        {"etiqueta": "A", "texto": "Opción A"},
                        {"etiqueta": "B", "texto": "Opción B"},
                        {"etiqueta": "C", "texto": "Opción C"},
                        {"etiqueta": "D", "texto": "Opción D"}
                      ],
                      "etiquetaCorrecta": "A"
                    }
                  ]
                }
                """ +
                "\nTexto fuente:\n%s",
                cantidad, dificultad.name(), texto
        );
    }

    public String construirPromptExplicacion(Pregunta pregunta) {
        Opcion correcta = pregunta.obtenerOpcionCorrecta();
        return String.format(
                "Pregunta: %s\nRespuesta correcta: %s) %s\n\n" +
                "Explica en 2-3 oraciones claras y concisas por qué esta es la respuesta correcta.",
                pregunta.getTextoPregunta(),
                correcta != null ? correcta.getEtiqueta() : "?",
                correcta != null ? correcta.getTexto() : "?"
        );
    }

    public List<Pregunta> parsearPreguntas(String json, String quizId) {
        try {
            String jsonLimpio = json.trim().replaceAll("```json\\n?", "").replaceAll("```\\n?", "").trim();
            JsonNode raiz = objectMapper.readTree(jsonLimpio);
            JsonNode nodosPreguntas = raiz.path("preguntas");
            List<Pregunta> resultado = new ArrayList<>();

            for (int i = 0; i < nodosPreguntas.size(); i++) {
                JsonNode nodo = nodosPreguntas.get(i);
                String etiquetaCorrecta = nodo.path("etiquetaCorrecta").asText();

                Pregunta pregunta = new Pregunta(quizId, nodo.path("textoPregunta").asText(), null, i);

                String idOpcionCorrecta = null;
                for (JsonNode nodoOpcion : nodo.path("opciones")) {
                    Opcion opcion = new Opcion(null, nodoOpcion.path("etiqueta").asText(), nodoOpcion.path("texto").asText());
                    pregunta.agregarOpcion(opcion);
                    if (opcion.getEtiqueta().equals(etiquetaCorrecta)) {
                        idOpcionCorrecta = opcion.getId();
                    }
                }

                pregunta.setOpcionCorrectaId(idOpcionCorrecta);
                resultado.add(pregunta);
            }

            return resultado;
        } catch (Exception e) {
            log.error("Error parseando JSON de preguntas: {}", e.getMessage());
            throw new ServicioIAException("La IA devolvió un formato inesperado. Intenta de nuevo.", e);
        }
    }
}
