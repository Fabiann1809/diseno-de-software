package com.biblioteca.service.impl;

import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.dto.UsuarioRequest;
import com.biblioteca.dto.UsuarioResponse;
import com.biblioteca.model.Bibliotecario;
import com.biblioteca.model.Estudiante;
import com.biblioteca.model.Profesor;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.PrestamoRepository;
import com.biblioteca.repository.UsuarioRepository;
import com.biblioteca.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PrestamoRepository prestamoRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              PrestamoRepository prestamoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        Usuario usuario = construirUsuario(request);
        return mapToResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse actualizarUsuario(String id, UsuarioRequest request) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        Usuario usuario = construirUsuario(request);
        usuario.setId(id);
        return mapToResponse(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminarUsuario(String id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponse consultarUsuario(String id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        return mapToResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrestamoResponse> consultarPrestamos(String usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuario no encontrado con id: " + usuarioId);
        }
        return prestamoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(p -> new PrestamoResponse(
                        p.getId(), p.getUsuarioId(), p.getEjemplarId(),
                        p.getFechaPrestamo(), p.getFechaDevolucionEsperada(),
                        p.getFechaDevolucionReal(), p.getEstado(), p.calcularMora()))
                .collect(Collectors.toList());
    }

    private Usuario construirUsuario(UsuarioRequest request) {
        Usuario usuario = switch (request.getTipoUsuario()) {
            case "ESTUDIANTE" -> {
                Estudiante e = new Estudiante();
                e.setCodigoEstudiante(request.getCodigoEstudiante());
                e.setPrograma(request.getPrograma());
                yield e;
            }
            case "PROFESOR" -> {
                Profesor p = new Profesor();
                p.setCodigoProfesor(request.getCodigoProfesor());
                p.setFacultad(request.getFacultad());
                yield p;
            }
            case "BIBLIOTECARIO" -> {
                Bibliotecario b = new Bibliotecario();
                b.setCodigoEmpleado(request.getCodigoEmpleado());
                b.setTurno(request.getTurno());
                yield b;
            }
            default -> throw new RuntimeException("Tipo de usuario no valido: " + request.getTipoUsuario());
        };
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        return usuario;
    }

    public UsuarioResponse mapToResponse(Usuario u) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(u.getId());
        response.setNombre(u.getNombre());
        response.setCorreo(u.getCorreo());

        if (u instanceof Estudiante e) {
            response.setTipoUsuario("ESTUDIANTE");
            response.setCodigoEstudiante(e.getCodigoEstudiante());
            response.setPrograma(e.getPrograma());
        } else if (u instanceof Profesor p) {
            response.setTipoUsuario("PROFESOR");
            response.setCodigoProfesor(p.getCodigoProfesor());
            response.setFacultad(p.getFacultad());
        } else if (u instanceof Bibliotecario b) {
            response.setTipoUsuario("BIBLIOTECARIO");
            response.setCodigoEmpleado(b.getCodigoEmpleado());
            response.setTurno(b.getTurno());
        }

        return response;
    }
}
