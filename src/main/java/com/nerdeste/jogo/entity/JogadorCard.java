package com.nerdeste.jogo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "jogador_cards", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"jogador_id", "card_id"})
	})
public class JogadorCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "jogador_id", nullable = false)
	@JsonIgnoreProperties
	private User jogador;
	
	@ManyToOne
	@JoinColumn(name = "card_id", nullable = false)
	@JsonIgnoreProperties
	private Card card;
	
	@Column(nullable = false)
	private LocalDateTime dataObtencao;
	
	
	public JogadorCard() {
		this.dataObtencao = LocalDateTime.now();
		
	}


	public JogadorCard(User jogador, Card card) {
		super();
		this.jogador = jogador;
		this.card = card;
		
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


	public Card getCard() {
		return card;
	}


	public void setCard(Card card) {
		this.card = card;
	}


	public LocalDateTime getDataObtencao() {
		return dataObtencao;
	}


	public void setDataObtencao(LocalDateTime dataObtencao) {
		this.dataObtencao = dataObtencao;
	}
	
	

}


