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

import com.nerdeste.jogo.entity.Card;
import com.nerdeste.jogo.service.CardService;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "*")
public class CardController {
    
    @Autowired
    private CardService cardService;
    
    
    // GET /api/cards
    @GetMapping
    public ResponseEntity<List<Card>> listarTodos() {
        try {
            List<Card> cards = cardService.listarTodos();
            return ResponseEntity.ok(cards);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    // GET /api/cards/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Card card = cardService.buscarPorId(id);
            return ResponseEntity.ok(card);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Card não encontrado: " + e.getMessage());
        }
    }
}