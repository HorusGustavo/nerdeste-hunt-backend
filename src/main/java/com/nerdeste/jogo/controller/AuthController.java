package com.nerdeste.jogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nerdeste.jogo.dto.LoginRequest;
import com.nerdeste.jogo.dto.RegistroRequest;
import com.nerdeste.jogo.entity.User;
import com.nerdeste.jogo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (importante pro frontend)
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    
    // POST /api/auth/registro
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest request) {
        try {
            // Chama o service para registrar
            User novoUsuario = authService.registrarJogador(
                request.getUserName(), 
                request.getSenha()
            );
            
            // Retorna o usuário criado com status 201 CREATED
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
            
        } catch (RuntimeException e) {
            // Se der erro, retorna mensagem de erro com status 400 BAD REQUEST
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao registrar: " + e.getMessage());
        }
    }
    
    
    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Chama o service para fazer login
            User usuarioLogado = authService.login(
                request.getUserName(), 
                request.getSenha()
            );
            
            // Retorna o usuário logado com status 200 OK
            return ResponseEntity.ok(usuarioLogado);
            
        } catch (RuntimeException e) {
            // Se der erro, retorna mensagem de erro com status 401 UNAUTHORIZED
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Erro ao fazer login: " + e.getMessage());
        }
    }
}