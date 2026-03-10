package com.nerdeste.jogo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerdeste.jogo.entity.User;
import com.nerdeste.jogo.repository.UserRepository;
import com.nerdeste.jogo.service.JogadorService;

@Service
public class AuthService {
	
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final JogadorService jogadorService;
	
	public AuthService(UserRepository userRepository,JogadorService jogadorService) {
		this.userRepository = userRepository;
		this.jogadorService = jogadorService;
	}
	
	
	
	public void validarJogador(User user) {
		if(user.getUserName() == null || user.getUserName().trim().isEmpty()) {
			throw new RuntimeException("O UserName é obrigatorio");
		}
		if(user.getSenha() == null || user.getSenha().trim().isEmpty()) {
			throw new RuntimeException("A Senha é obrigatorio");
		}
		if(userRepository.existsByUserName(user.getUserName())) {
			throw new RuntimeException("O UserName já Existe");
		}
		

	}
	
	public User registrarJogador(String userName, String senha) {
		User user = new User();
		user.setUserName(userName);
		user.setSenha(senha);
		
		validarJogador(user);
		
		User jogadorSalvo = userRepository.save(user);
		
		// ⬇️ ADICIONA ESSA LINHA AQUI
		// Inicializa os 12 desafios para o jogador
		jogadorService.inicializarDesafiosParaJogador(jogadorSalvo.getId());
		
		return userRepository.save(user);
	
	}
	
public User login(String userName, String senha) {
		
	
		Optional<User> userOptional = userRepository.findByUserName(userName);
		
		
		if (userOptional.isEmpty()) {
			throw new RuntimeException("Usuário não encontrado");
		}
		
		
		User user = userOptional.get();
		
		
		if (!user.getSenha().equals(senha)) {
			throw new RuntimeException("Senha incorreta");
		}
		
		
		return user;
	}

}
	
	


