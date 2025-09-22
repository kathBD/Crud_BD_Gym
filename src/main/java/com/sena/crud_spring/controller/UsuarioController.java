package com.sena.crud_spring.controller;

import com.sena.crud_spring.model.Rol;
import com.sena.crud_spring.model.Usuario;
import com.sena.crud_spring.repository.IRolRepository;
import com.sena.crud_spring.service.RolService;
import com.sena.crud_spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private RolService rolService;



    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // Buscar usuarios por nombre de rol (CLIENTE, ENTRENADOR, ADMINISTRADOR)
    @GetMapping("/rol/{nombreRol}")
    public List<Usuario> buscarPorRol(@PathVariable String nombreRol) {
        return usuarioService.getUsuariosPorRol(nombreRol);
    }

    // Crear un nuevo usuario (con contraseña cifrada automáticamente)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        Usuario usuarioGuardado = usuarioService.guardarUsuario(nuevoUsuario);
        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.getAllRoles());
        return "registro_usuario";  // Thymeleaf buscará usuarios/registro_usuario.html
    }

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }




}


