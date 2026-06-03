package com.quizai.service;

import com.quizai.dto.LoginRequest;
import com.quizai.dto.RegistroRequest;
import com.quizai.dto.TokenDTO;
import com.quizai.dto.UsuarioDTO;

public interface AuthService {
    UsuarioDTO registrar(RegistroRequest request);
    TokenDTO iniciarSesion(LoginRequest request);
}
