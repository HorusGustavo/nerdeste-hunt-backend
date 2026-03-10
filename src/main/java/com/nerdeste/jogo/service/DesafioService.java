package com.nerdeste.jogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerdeste.jogo.entity.Desafio;
import com.nerdeste.jogo.repository.DesafioRepository;

@Service
public class DesafioService {
	
	@Autowired
	private final DesafioRepository desafioRepository;
	
	public DesafioService(DesafioRepository desafioRepository) {
		this.desafioRepository = desafioRepository;
	}
	
	public List<Desafio> listarTodosAtivos(){
		return desafioRepository.findByAtivoTrue();
	}
	
	
	public Desafio buscarPorId(Long id) {
		return desafioRepository.findById(id).orElseThrow(() -> new RuntimeException("Desafio Não Encontrado"));
		
	}
	
	public Desafio buscarPorToken(String qrCodeToken) {
		return desafioRepository.findByQrCodeToken(qrCodeToken).orElseThrow(() -> new RuntimeException("QR Code Inválido"));
	}
	

}


