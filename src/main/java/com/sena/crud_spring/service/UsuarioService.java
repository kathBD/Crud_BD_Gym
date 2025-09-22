package com.sena.crud_spring.service;

import com.sena.crud_spring.model.Rol;
import com.sena.crud_spring.model.Usuario;
import com.sena.crud_spring.repository.IRolRepository;
import com.sena.crud_spring.repository.IUsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que maneja la lógica de negocio relacionada con los usuarios.
 * Esta clase se comunica con los repositorios para acceder a la base de datos.
 */
@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Guarda un nuevo usuario en la base de datos,
     * asegurándose de cifrar la contraseña y asignar correctamente el rol.
     *
     * @param usuario Objeto usuario a guardar.
     * @return Usuario guardado (con ID generado si corresponde).
     */
    public Usuario guardarUsuario(Usuario usuario) {
        // Verificar y asignar el rol si viene con ID
        if (usuario.getRol() != null && usuario.getRol().getRolId() != null) {
            Rol rol = rolRepository.findById(usuario.getRol().getRolId())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            usuario.setRol(rol);
        }

        // Solo codificar si la contraseña está presente (y opcionalmente si cambió)
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            String passwordCifrada = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passwordCifrada);
        }

        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene la lista completa de usuarios registrados en el sistema.
     *
     * @return Lista de usuarios.
     */
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtiene todos los usuarios que tienen asignado un rol específico (por nombre).
     * El nombre del rol no es sensible a mayúsculas/minúsculas.
     *
     * @param nombreRol Nombre del rol.
     * @return Lista de usuarios filtrados por el rol especificado.
     */
    public List<Usuario> getUsuariosPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombreIgnoreCase(nombreRol);
    }

    /**
     * Método para cifrar todas las contraseñas que están en texto plano.
     * Ideal para usar una sola vez para migrar las contraseñas existentes.
     */
    @PostConstruct
    public void cifrarTodasLasContrasenas() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario u : usuarios) {
            if (u.getPassword() != null && !u.getPassword().startsWith("$2a$")) { // Verifica si no está cifrada
                String passCifrada = passwordEncoder.encode(u.getPassword());
                u.setPassword(passCifrada);
                usuarioRepository.save(u);
            }
        }
    }
}










