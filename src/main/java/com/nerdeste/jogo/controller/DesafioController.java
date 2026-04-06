package com.nerdeste.jogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerdeste.jogo.entity.Desafio;
import com.nerdeste.jogo.service.DesafioService;

@RestController
@RequestMapping("/api/desafios")
@CrossOrigin(origins = "*")
public class DesafioController {
    
    @Autowired
    private DesafioService desafioService;
    
    
    // GET /api/desafios
    @GetMapping
    public ResponseEntity<List<Desafio>> listarTodos() {
        try {
            // Busca todos os desafios ativos
            List<Desafio> desafios = desafioService.listarTodosAtivos();
            
            // Retorna a lista com status 200 OK
            return ResponseEntity.ok(desafios);
            
        } catch (RuntimeException e) {
            // Se der erro, retorna lista vazia
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    // GET /api/desafios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            // Busca desafio por ID
            Desafio desafio = desafioService.buscarPorId(id);
            
            // Retorna o desafio com status 200 OK
            return ResponseEntity.ok(desafio);
            
        } catch (RuntimeException e) {
            // Se não achar, retorna 404 NOT FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Desafio não encontrado: " + e.getMessage());
        }
    }
}
