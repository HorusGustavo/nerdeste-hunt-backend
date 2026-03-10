package com.nerdeste.jogo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nerdeste.jogo.entity.User;
import com.nerdeste.jogo.service.RankingService;

@RestController
@RequestMapping("/api/ranking")
@CrossOrigin(origins = "*")
public class RankingController {
    
    @Autowired
    private RankingService rankingService;
    
    
    // GET /api/ranking/top10
    @GetMapping("/top10")
    public ResponseEntity<?> obterTop10() {
        try {
            // Busca o top 10
            List<User> top10Users = rankingService.obterTop10();
            
            // Formata a resposta
            List<Map<String, Object>> ranking = new ArrayList<>();
            
            for (int i = 0; i < top10Users.size(); i++) {
                User user = top10Users.get(i);
                
                Map<String, Object> item = new HashMap<>();
                item.put("posicao", i + 1);
                item.put("userName", user.getUserName());
                item.put("tempoTotalSegundos", user.getTempoTotalSegundos());
                item.put("tempoFormatado", formatarTempo(user.getTempoTotalSegundos()));
                item.put("dataConclusao", user.getDataConclusaoJogo());
                
                ranking.add(item);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("ranking", ranking);
            response.put("totalJogadoresCompletos", top10Users.size());
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar ranking: " + e.getMessage());
        }
    }
    
    
    // GET /api/ranking/posicao?jogadorId=5
    @GetMapping("/posicao")
    public ResponseEntity<?> obterPosicao(@RequestParam(name = "jogadorId") Long jogadorId) {
        try {
            // Busca a posição do jogador
            Integer posicao = rankingService.obterPosicaoJogador(jogadorId);
            
            // Monta a resposta
            Map<String, Object> response = new HashMap<>();
            response.put("jogadorId", jogadorId);
            response.put("posicao", posicao);
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro: " + e.getMessage());
        }
    }
    
    
    // Método auxiliar para formatar tempo
    private String formatarTempo(Long segundos) {
        if (segundos == null) return "N/A";
        
        long horas = segundos / 3600;
        long minutos = (segundos % 3600) / 60;
        long segs = segundos % 60;
        
        return String.format("%dh %dmin %ds", horas, minutos, segs);
    }
}
