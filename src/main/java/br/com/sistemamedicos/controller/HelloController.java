package br.com.sistemamedicos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {
    // esse endpoint foi util pra testar se a API subia antes do CRUD.

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Hello world",
                "project", "Sistema Medicos API"
        );
    }
}
