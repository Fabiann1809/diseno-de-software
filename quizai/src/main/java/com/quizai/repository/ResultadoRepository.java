package com.quizai.repository;

import com.quizai.model.Resultado;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ResultadoRepository extends MongoRepository<Resultado, String> {
    Optional<Resultado> findByQuizId(String quizId);
    boolean existsByQuizId(String quizId);
}
