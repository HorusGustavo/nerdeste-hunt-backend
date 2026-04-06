package com.nerdeste.jogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerdeste.jogo.entity.Card;
import com.nerdeste.jogo.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private final CardRepository cardRepository;
	
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	
	public Card buscarPorDesafioId(Long desafioId) {
		return cardRepository.findByDesafioId(desafioId).orElseThrow(() -> new RuntimeException("Card Não Encontrado"));
	}
	
	public List<Card>listarTodos(){
		return cardRepository.findAll();
	}
	
	public Card buscarPorId(Long id) {
		return cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card Não Encontrado"));
	}

}


