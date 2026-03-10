package com.nerdeste.jogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nerdeste.jogo.entity.JogadorDesafio;



@Repository
public interface JogadorDesafioRepository extends JpaRepository <JogadorDesafio, Long> {
	
	List<JogadorDesafio>findByJogadorId(Long jogadorId);
	
	Optional<JogadorDesafio>findByJogadorIdAndDesafioId(Long jogadorId, Long desafioId);

}


