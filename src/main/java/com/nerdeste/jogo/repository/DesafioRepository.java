package com.nerdeste.jogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nerdeste.jogo.entity.Desafio;




@Repository
public interface DesafioRepository extends JpaRepository <Desafio, Long> {
	
	List<Desafio>findByAtivoTrue();
	Optional<Desafio> findByQrCodeToken(String qrCodeToken);

}


