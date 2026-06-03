package com.quizai.repository;

import com.quizai.model.Pregunta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PreguntaRepository extends MongoRepository<Pregunta, String> {
    List<Pregunta> findByQuizIdOrderByOrdenAsc(String quizId);
    void deleteByQuizId(String quizId);
}
