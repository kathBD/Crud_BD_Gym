package com.sena.crud_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "inicio";
    }


        @GetMapping("/admin")
        public String adminPage() {
            return "admin";
        }

        @GetMapping("/cliente")
        public String clientePage() {
            return "cliente";
        }

        @GetMapping("/entrenador")
        public String entrenadorPage() {
            return "entrenador";
        }
    }


