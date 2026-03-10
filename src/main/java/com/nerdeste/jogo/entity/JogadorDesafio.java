package com.nerdeste.jogo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "jogador_desafios", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"jogador_id", "desafio_id"})
	})
public class JogadorDesafio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "jogador_id", nullable = false)
	@JsonIgnoreProperties
	private User jogador;
	
	@ManyToOne
	@JoinColumn(name = "desafio_id", nullable = false)
	@JsonIgnoreProperties
	private Desafio desafio;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusDesafio status;
	
	@Column(nullable = true)
	private LocalDateTime dataInicio;
	
	@Column(nullable = true)
	private LocalDateTime dataConclusao;
	
	
	
	
	public enum StatusDesafio{
		PENDENTE,
		EM_ANDAMENTO,
		CONCLUIDO
		
	}
	
	public JogadorDesafio() {
		this.status = StatusDesafio.PENDENTE;
		
	}

	public JogadorDesafio(User jogador, Desafio desafio) {
		this.jogador = jogador;
		this.desafio = desafio;
		this.status = StatusDesafio.PENDENTE;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getJogador() {
		return jogador;
	}

	public void setJogador(User jogador) {
		this.jogador = jogador;
	}

	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}

	public StatusDesafio getStatus() {
		return status;
	}

	public void setStatus(StatusDesafio status) {
		this.status = status;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	
	
}



