package com.nerdeste.jogo.dto;

public class RegistroRequest {
    
    private String userName;
    private String senha;
    
    // Construtores
    public RegistroRequest() {
    }
    
    public RegistroRequest(String userName, String senha) {
        this.userName = userName;
        this.senha = senha;
    }
    
    // Getters e Setters
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
}
