package com.nerdeste.jogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nerdeste.jogo.entity.JogadorCard;



@Repository
public interface JogadorCardRepository extends JpaRepository <JogadorCard, Long> {
	
	List<JogadorCard>findByJogadorId(Long jogadorId);
	
	Optional<JogadorCard>findByJogadorIdAndCardId(Long jogadorId, Long cardId);

}

