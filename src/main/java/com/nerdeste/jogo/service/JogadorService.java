package com.nerdeste.jogo.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerdeste.jogo.entity.Card;
import com.nerdeste.jogo.entity.Desafio;
import com.nerdeste.jogo.entity.JogadorCard;
import com.nerdeste.jogo.entity.JogadorDesafio;
import com.nerdeste.jogo.entity.JogadorDesafio.StatusDesafio;
import com.nerdeste.jogo.entity.User;
import com.nerdeste.jogo.repository.CardRepository;
import com.nerdeste.jogo.repository.DesafioRepository;
import com.nerdeste.jogo.repository.JogadorCardRepository;
import com.nerdeste.jogo.repository.JogadorDesafioRepository;
import com.nerdeste.jogo.repository.UserRepository;
import com.nerdeste.jogo.service.CardService;

@Service
public class JogadorService {
	
	@Autowired
	private final JogadorDesafioRepository jogadorDesafioRepository;
	@Autowired
	private final JogadorCardRepository jogadorCardRepository;
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final DesafioRepository desafioRepository;
	@Autowired
	private final CardRepository cardRepository;
	@Autowired
	private final CardService cardService;
	
	public JogadorService(JogadorDesafioRepository jogadorDesafioRepository, JogadorCardRepository jogadorCardRepository, UserRepository userRepository, DesafioRepository desafioRepository, CardRepository cardRepository, CardService cardService) {
		this.jogadorDesafioRepository = jogadorDesafioRepository;
		this.jogadorCardRepository = jogadorCardRepository;
		this.userRepository = userRepository;
		this.desafioRepository = desafioRepository;
		this.cardRepository = cardRepository;
		this.cardService = cardService;
	}
	
	
	public void inicializarDesafiosParaJogador(Long jogadorId) {
	    
	    // 1. Busca o jogador no banco
	    User jogador = userRepository.findById(jogadorId)
	            .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
	    
	    // 2. Busca todos os 12 desafios ativos
	    List<Desafio> desafios = desafioRepository.findByAtivoTrue();
	    
	    // 3. Cria uma lista vazia para guardar os JogadorDesafio
	    List<JogadorDesafio> jogadorDesafios = new ArrayList<>();
	    
	    // 4. Loop por cada desafio ativo
	    for (Desafio desafio : desafios) {
	        
	        // 5. Cria um novo JogadorDesafio para esse desafio
	        JogadorDesafio jogadorDesafio = new JogadorDesafio();
	        jogadorDesafio.setJogador(jogador);
	        jogadorDesafio.setDesafio(desafio);
	        // status já é PENDENTE por padrão (construtor da entidade)
	        // dataInicio e dataConclusao já são null por padrão
	        
	        // 6. Adiciona na lista
	        jogadorDesafios.add(jogadorDesafio);
	    }
	    
	    // 7. Salva TODOS os 12 registros de uma vez no banco
	    jogadorDesafioRepository.saveAll(jogadorDesafios);
	}
	
	public List<JogadorDesafio> obterProgressoJogador(Long jogadorId) {
	    
	    // 1. Busca todos os JogadorDesafio do jogador
	    List<JogadorDesafio> progressoDesafios = jogadorDesafioRepository.findByJogadorId(jogadorId);
	    
	    // 2. Verifica se o jogador tem desafios
	    if (progressoDesafios.isEmpty()) {
	        throw new RuntimeException("Nenhum desafio encontrado para este jogador");
	    }
	    
	    // 3. (OPCIONAL) Conta quantos estão concluídos
	    long totalConcluidos = progressoDesafios.stream()
	            .filter(jd -> jd.getStatus() == StatusDesafio.CONCLUIDO)
	            .count();
	    
	    // 4. (OPCIONAL) Exibe no console para debug
	    System.out.println("Progresso do jogador " + jogadorId + ":");
	    System.out.println("Total de desafios: " + progressoDesafios.size());
	    System.out.println("Desafios concluídos: " + totalConcluidos);
	    System.out.println("Desafios pendentes: " + (progressoDesafios.size() - totalConcluidos));
	    
	    // 5. Retorna a lista completa
	    return progressoDesafios;
	}
	
	
		
	// 3. VALIDAR DESAFIO (MÉTODO PRINCIPAL!)
		public JogadorDesafio validarDesafio(Long jogadorId, Long desafioId, String qrCodeToken) {
			
			// PASSO 1: Validar QR Code
			Desafio desafio = desafioRepository.findByQrCodeToken(qrCodeToken)
					.orElseThrow(() -> new RuntimeException("QR Code inválido"));
			
			// Verifica se o desafioId bate com o QR Code
			if (!desafio.getId().equals(desafioId)) {
				throw new RuntimeException("QR Code não corresponde ao desafio");
			}
			
			// PASSO 2: Buscar JogadorDesafio
			JogadorDesafio jogadorDesafio = jogadorDesafioRepository
					.findByJogadorIdAndDesafioId(jogadorId, desafioId)
					.orElseThrow(() -> new RuntimeException("Registro de desafio não encontrado"));
			
			// PASSO 3: Validar se já foi concluído
			if (jogadorDesafio.getStatus() == StatusDesafio.CONCLUIDO) {
				throw new RuntimeException("Desafio já foi concluído anteriormente");
			}
			
			// PASSO 4: Marcar como concluído
			jogadorDesafio.setStatus(StatusDesafio.CONCLUIDO);
			jogadorDesafio.setDataConclusao(LocalDateTime.now());
			
			// Se dataInicio for null, preenche também
			if (jogadorDesafio.getDataInicio() == null) {
				jogadorDesafio.setDataInicio(LocalDateTime.now());
			}
			
			// Salva
			jogadorDesafioRepository.save(jogadorDesafio);
			
			// PASSO 5: Atualizar dataInicioJogo do User (se for o primeiro desafio)
			User jogador = userRepository.findById(jogadorId)
					.orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
			
			if (jogador.getDataInicioJogo() == null) {
				// É o primeiro desafio concluído!
				jogador.setDataInicioJogo(LocalDateTime.now());
				userRepository.save(jogador);
			}
			
			// PASSO 6: Liberar card
			Card card = cardService.buscarPorDesafioId(desafioId);
			liberarCard(jogadorId, card.getId());
			
			// PASSO 7: Verificar se completou todos os 12
			boolean jogoCompleto = verificarConclusaoJogo(jogadorId);
			
			if (jogoCompleto) {
				System.out.println("🎉 PARABÉNS! Jogador " + jogadorId + " completou todos os desafios!");
			}
			
			return jogadorDesafio;
		}
		
		
		// 4. LIBERAR CARD
		public void liberarCard(Long jogadorId, Long cardId) {
			
			// Busca o jogador
			User jogador = userRepository.findById(jogadorId)
					.orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
			
			// Busca o card
			Card card = cardService.buscarPorDesafioId(cardId); // Usa o cardId como desafioId
			
			// Verifica se o jogador já tem esse card
			var cardExistente = jogadorCardRepository.findByJogadorIdAndCardId(jogadorId, cardId);
			
			if (cardExistente.isPresent()) {
				// Já tem o card, não faz nada
				System.out.println("Jogador já possui este card");
				return;
			}
			
			// Cria novo JogadorCard
			JogadorCard jogadorCard = new JogadorCard();
			jogadorCard.setJogador(jogador);
			jogadorCard.setCard(card);
			// dataObtencao já é preenchida no construtor
			
			// Salva
			jogadorCardRepository.save(jogadorCard);
			
			System.out.println("✅ Card liberado: " + card.getNome() + " (" + card.getRaridade() + ")");
		}
		
		
		// 5. VERIFICAR SE COMPLETOU O JOGO
		public boolean verificarConclusaoJogo(Long jogadorId) {
			
			// Busca todos os JogadorDesafio do jogador
			List<JogadorDesafio> todosDesafios = jogadorDesafioRepository.findByJogadorId(jogadorId);
			
			// Conta quantos estão concluídos
			long totalConcluidos = todosDesafios.stream()
					.filter(jd -> jd.getStatus() == StatusDesafio.CONCLUIDO)
					.count();
			
			// Se completou os 12
			if (totalConcluidos == 12) {
				
				// Busca o jogador
				User jogador = userRepository.findById(jogadorId)
						.orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
				
				// Verifica se já tinha sido marcado como completo antes
				if (jogador.getDataConclusaoJogo() != null) {
					return true; // Já estava completo
				}
				
				// Marca como completo agora
				jogador.setDataConclusaoJogo(LocalDateTime.now());
				
				// Calcula o tempo total em segundos
				if (jogador.getDataInicioJogo() != null) {
					Duration duracao = Duration.between(
							jogador.getDataInicioJogo(), 
							jogador.getDataConclusaoJogo()
					);
					jogador.setTempoTotalSegundos(duracao.getSeconds());
				}
				
				// Salva
				userRepository.save(jogador);
				
				return true;
			}
			
			return false;
		}
		
		
		// 6. OBTER COLEÇÃO DE CARDS
		public List<JogadorCard> obterColecaoCards(Long jogadorId) {
			
			// Busca todos os JogadorCard do jogador
			List<JogadorCard> colecao = jogadorCardRepository.findByJogadorId(jogadorId);
			
			// (Opcional) Conta estatísticas
			long totalBasicos = colecao.stream()
					.filter(jc -> jc.getCard().getRaridade() == Card.Raridade.BASICO)
					.count();
			
			long totalRaros = colecao.stream()
					.filter(jc -> jc.getCard().getRaridade() == Card.Raridade.RARO)
					.count();
			
			long totalLendarios = colecao.stream()
					.filter(jc -> jc.getCard().getRaridade() == Card.Raridade.LENDARIO)
					.count();
			
			// Debug
			System.out.println("📚 Coleção do jogador " + jogadorId + ":");
			System.out.println("Total de cards: " + colecao.size());
			System.out.println("Básicos: " + totalBasicos);
			System.out.println("Raros: " + totalRaros);
			System.out.println("Lendários: " + totalLendarios);
			
			return colecao;
		}
	
	
}
