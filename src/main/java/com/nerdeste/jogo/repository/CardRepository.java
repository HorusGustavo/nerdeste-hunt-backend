package com.nerdeste.jogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nerdeste.jogo.entity.Card;


@Repository
public interface CardRepository extends JpaRepository <Card, Long>{
	
	Optional<Card>findByDesafioId(Long desafioId);

}


