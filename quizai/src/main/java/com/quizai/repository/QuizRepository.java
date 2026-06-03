package com.quizai.repository;

import com.quizai.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    List<Quiz> findByUsuarioId(String usuarioId);
}
