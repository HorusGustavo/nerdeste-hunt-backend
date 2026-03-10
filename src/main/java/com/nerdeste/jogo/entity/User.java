package com.nerdeste.jogo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String userName;
	
	@Column(nullable = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@Column(nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(nullable = true)
	private LocalDateTime dataInicioJogo;
	
	@Column(nullable = true)
	private LocalDateTime dataConclusaoJogo;
	
	@Column(nullable = true)
	private Long tempoTotalSegundos;
	
	
	
	public enum Role{
		JOGADOR,
		ADMIN
	}
	
	public User() {
		this.dataCriacao = LocalDateTime.now();
		this.role = Role.JOGADOR;
		
	}
	
	public User(String userName,String senha,LocalDateTime dataInicioJogo, LocalDateTime dataConclusaoJogo, Long tempoTotalSegundos) {
		
		this.userName = userName;
		this.senha = senha;
		this.dataInicioJogo = dataInicioJogo;
		this.dataConclusaoJogo = dataConclusaoJogo;
		this.tempoTotalSegundos = tempoTotalSegundos;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataInicioJogo() {
		return dataInicioJogo;
	}

	public void setDataInicioJogo(LocalDateTime dataInicioJogo) {
		this.dataInicioJogo = dataInicioJogo;
	}

	public LocalDateTime getDataConclusaoJogo() {
		return dataConclusaoJogo;
	}

	public void setDataConclusaoJogo(LocalDateTime dataConclusaoJogo) {
		this.dataConclusaoJogo = dataConclusaoJogo;
	}

	public Long getTempoTotalSegundos() {
		return tempoTotalSegundos;
	}

	public void setTempoTotalSegundos(Long tempoTotalSegundos) {
		this.tempoTotalSegundos = tempoTotalSegundos;
	}
	
	
	

}


