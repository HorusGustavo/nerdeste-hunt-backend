package com.nerdeste.jogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerdeste.jogo.entity.User;
import com.nerdeste.jogo.repository.UserRepository;

@Service
public class RankingService {
	
	@Autowired
	private final UserRepository userRepository;
	
	public RankingService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	// 1. OBTER TOP 10 DO RANKING
	public List<User> obterTop10() {
		
		// Busca os top 10 jogadores que completaram o jogo
		List<User> top10 = userRepository
				.findTop10ByDataConclusaoJogoIsNotNullOrderByTempoTotalSegundosAsc();
		
		// Debug
		System.out.println("🏆 TOP 10 RANKING:");
		for (int i = 0; i < top10.size(); i++) {
			User user = top10.get(i);
			System.out.println((i + 1) + "º - " + user.getUserName() + 
					" (" + formatarTempo(user.getTempoTotalSegundos()) + ")");
		}
		
		return top10;
	}
	
	
	// 2. OBTER POSIÇÃO DO JOGADOR
	public Integer obterPosicaoJogador(Long jogadorId) {
		
		// Busca TODOS que completaram (sem limite)
		List<User> todosCompletos = userRepository
				.findByDataConclusaoJogoIsNotNullOrderByTempoTotalSegundosAsc();
		
		// Procura o jogador na lista
		for (int i = 0; i < todosCompletos.size(); i++) {
			if (todosCompletos.get(i).getId().equals(jogadorId)) {
				return i + 1; // Posição (índice + 1)
			}
		}
		
		// Se não achou, jogador não completou ainda
		throw new RuntimeException("Jogador ainda não completou o jogo ou não existe");
	}
	
	
	// MÉTODO AUXILIAR: Formatar tempo em horas/minutos
	private String formatarTempo(Long segundos) {
		if (segundos == null) return "N/A";
		
		long horas = segundos / 3600;
		long minutos = (segundos % 3600) / 60;
		long segs = segundos % 60;
		
		return String.format("%dh %dmin %ds", horas, minutos, segs);
	}
}