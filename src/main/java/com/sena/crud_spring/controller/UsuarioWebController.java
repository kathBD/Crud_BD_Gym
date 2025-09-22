package com.sena.crud_spring.controller;

import com.sena.crud_spring.model.Usuario;
import com.sena.crud_spring.model.Rol;
import com.sena.crud_spring.service.RolService;
import com.sena.crud_spring.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioWebController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {
        List<Rol> roles = rolService.getAllRoles();
        System.out.println("Roles obtenidos: " + roles);

        Usuario usuario = new Usuario();
        usuario.setRol(new Rol());

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);

        return "registro_usuario";
    }


    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            System.out.println("Errores de validación: " + result.getAllErrors());
            model.addAttribute("roles", rolService.getAllRoles());
            return "registro_usuario";
        }

        try {
            usuarioService.guardarUsuario(usuario);
            return "redirect:/usuarios";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al guardar el usuario: " + e.getMessage());
            model.addAttribute("roles", rolService.getAllRoles());
            return "registro_usuario";
        }
    }
}



