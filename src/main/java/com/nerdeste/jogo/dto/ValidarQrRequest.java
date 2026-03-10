package com.nerdeste.jogo.dto;

public class ValidarQrRequest {
    
    private Long jogadorId;
    private Long desafioId;
    private String qrCodeToken;
    
    // Construtores
    public ValidarQrRequest() {
    }
    
    public ValidarQrRequest(Long jogadorId, Long desafioId, String qrCodeToken) {
        this.jogadorId = jogadorId;
        this.desafioId = desafioId;
        this.qrCodeToken = qrCodeToken;
    }
    
    // Getters e Setters
    public Long getJogadorId() {
        return jogadorId;
    }
    
    public void setJogadorId(Long jogadorId) {
        this.jogadorId = jogadorId;
    }
    
    public Long getDesafioId() {
        return desafioId;
    }
    
    public void setDesafioId(Long desafioId) {
        this.desafioId = desafioId;
    }
    
    public String getQrCodeToken() {
        return qrCodeToken;
    }
    
    public void setQrCodeToken(String qrCodeToken) {
        this.qrCodeToken = qrCodeToken;
    }
}