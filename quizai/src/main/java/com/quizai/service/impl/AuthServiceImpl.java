package com.quizai.service.impl;

import com.quizai.dto.LoginRequest;
import com.quizai.dto.RegistroRequest;
import com.quizai.dto.TokenDTO;
import com.quizai.dto.UsuarioDTO;
import com.quizai.exception.ConflictoException;
import com.quizai.exception.NoAutorizadoException;
import com.quizai.model.Usuario;
import com.quizai.repository.UsuarioRepository;
import com.quizai.security.JwtUtil;
import com.quizai.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder codificadorContrasena;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           PasswordEncoder codificadorContrasena,
                           JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.codificadorContrasena = codificadorContrasena;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UsuarioDTO registrar(RegistroRequest request) {
        if (usuarioRepository.existsByCorreo(request.correo())) {
            throw new ConflictoException("El correo ya está registrado: " + request.correo());
        }

        Usuario usuario = new Usuario(
                request.nombre(),
                request.correo(),
                codificadorContrasena.encode(request.contrasena())
        );

        Usuario guardado = usuarioRepository.save(usuario);
        log.info("Nuevo usuario registrado: {}", guardado.getCorreo());
        return mapearADTO(guardado);
    }

    @Override
    public TokenDTO iniciarSesion(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByCorreo(request.correo())
                .orElseThrow(() -> new NoAutorizadoException("Credenciales inválidas"));

        if (!codificadorContrasena.matches(request.contrasena(), usuario.getContrasena())) {
            throw new NoAutorizadoException("Credenciales inválidas");
        }

        String token = jwtUtil.generarToken(usuario.getId());
        log.info("Sesión iniciada para usuario: {}", usuario.getCorreo());
        return new TokenDTO(token, jwtUtil.getExpiracionMs());
    }

    private UsuarioDTO mapearADTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getCorreo());
    }
}
