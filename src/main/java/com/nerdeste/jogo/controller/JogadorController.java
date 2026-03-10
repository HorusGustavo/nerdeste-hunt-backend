package com.nerdeste.jogo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nerdeste.jogo.dto.ValidarQrRequest;
import com.nerdeste.jogo.entity.Card;
import com.nerdeste.jogo.entity.JogadorCard;
import com.nerdeste.jogo.entity.JogadorDesafio;
import com.nerdeste.jogo.service.CardService;
import com.nerdeste.jogo.service.JogadorService;

@RestController
@RequestMapping("/api/jogador")
@CrossOrigin(origins = "*")
public class JogadorController {
    
    @Autowired
    private JogadorService jogadorService;
    
    @Autowired
    private CardService cardService;
    
    
    // GET /api/jogador/progresso?jogadorId=1
    @GetMapping("/progresso")
    public ResponseEntity<?> obterProgresso(@RequestParam(name = "jogadorId") Long jogadorId) {
        try {
            // Busca o progresso do jogador
            List<JogadorDesafio> progresso = jogadorService.obterProgressoJogador(jogadorId);
            
            // Retorna a lista de desafios com status
            return ResponseEntity.ok(progresso);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro ao buscar progresso: " + e.getMessage());
        }
    }
    
    
    // POST /api/jogador/validar-qr
    @PostMapping("/validar-qr")
    public ResponseEntity<?> validarQrCode(@RequestBody ValidarQrRequest request) {
        try {
            // Valida o QR Code
            JogadorDesafio jogadorDesafio = jogadorService.validarDesafio(
                request.getJogadorId(),
                request.getDesafioId(),
                request.getQrCodeToken()
            );
            
            // Busca o card liberado
            Card cardLiberado = cardService.buscarPorDesafioId(request.getDesafioId());
            
            // Verifica se completou o jogo
            boolean jogoCompleto = jogadorService.verificarConclusaoJogo(request.getJogadorId());
            
            // Conta total de desafios concluídos
            List<JogadorDesafio> todosDesafios = jogadorService.obterProgressoJogador(request.getJogadorId());
            long totalConcluidos = todosDesafios.stream()
                    .filter(jd -> jd.getStatus() == JogadorDesafio.StatusDesafio.CONCLUIDO)
                    .count();
            
            // Monta a resposta
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", jogoCompleto ? 
                    "Parabéns! Você completou todos os desafios!" : 
                    "Desafio concluído com sucesso!");
            response.put("jogadorDesafio", jogadorDesafio);
            response.put("cardLiberado", cardLiberado);
            response.put("jogoCompleto", jogoCompleto);
            response.put("totalConcluidos", totalConcluidos);
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            // Monta resposta de erro
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("sucesso", false);
            errorResponse.put("mensagem", e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    
    
    // GET /api/jogador/cards?jogadorId=1
    @GetMapping("/cards")
    public ResponseEntity<?> obterColecao(@RequestParam(name = "jogadorId") Long jogadorId) {
        try {
            // Busca a coleção de cards do jogador
            List<JogadorCard> colecao = jogadorService.obterColecaoCards(jogadorId);
            
            // Calcula estatísticas
            long totalBasicos = colecao.stream()
                    .filter(jc -> jc.getCard().getRaridade() == Card.Raridade.BASICO)
                    .count();
            
            long totalRaros = colecao.stream()
                    .filter(jc -> jc.getCard().getRaridade() == Card.Raridade.RARO)
                    .count();
            
            long totalLendarios = colecao.stream()
                    .filter(jc -> jc.getCard().getRaridade() == Card.Raridade.LENDARIO)
                    .count();
            
            // Monta a resposta
            Map<String, Object> response = new HashMap<>();
            response.put("cards", colecao);
            response.put("totalCards", colecao.size());
            
            Map<String, Long> estatisticas = new HashMap<>();
            estatisticas.put("basicos", totalBasicos);
            estatisticas.put("raros", totalRaros);
            estatisticas.put("lendarios", totalLendarios);
            response.put("estatisticas", estatisticas);
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro ao buscar coleção: " + e.getMessage());
        }
    }
}