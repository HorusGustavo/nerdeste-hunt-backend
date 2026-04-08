package com.nerdeste.jogo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Raridade raridade;
	
	@Column(name = "imagem_url", nullable = true)
	private String imagemUrl;
	
	@OneToOne
	@JoinColumn(name = "desafio_id")
	private Desafio desafio;
	
	
	
	
	
	public enum Raridade{
		BASICO,
		RARO,
		LENDARIO
	}
	
	public Card() {
		
	}

	public Card(String nome,String imagemUrl, Desafio desafio) {
		super();
		this.nome = nome;
		this.imagemUrl = imagemUrl;
		this.desafio = desafio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Raridade getRaridade() {
		return raridade;
	}

	public void setRaridade(Raridade raridade) {
		this.raridade = raridade;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}
	
	

}


