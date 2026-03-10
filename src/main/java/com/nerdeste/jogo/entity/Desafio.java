package com.nerdeste.jogo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "desafios")
public class Desafio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Column(nullable = false, length = 500)
	private String descricao;
	
	@Column(nullable = false, unique = true)
	private String qrCodeToken;
	
	@Column(nullable = false)
	private boolean ativo;
	
	
	
	public Desafio() {
		this.ativo = true;
		
	}



	public Desafio(String nome, String descricao, String qrCodeToken, boolean ativo) {
		this.nome = nome;
		this.descricao = descricao;
		this.qrCodeToken = qrCodeToken;
		this.ativo = ativo;
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



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public String getQrCodeToken() {
		return qrCodeToken;
	}



	public void setQrCodeToken(String qrCodeToken) {
		this.qrCodeToken = qrCodeToken;
	}



	public boolean getAtivo() {
		return ativo;
	}



	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
	

}

