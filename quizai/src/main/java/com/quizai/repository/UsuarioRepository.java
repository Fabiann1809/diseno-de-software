package com.quizai.repository;

import com.quizai.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
