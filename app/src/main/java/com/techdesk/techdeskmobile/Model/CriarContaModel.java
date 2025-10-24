package com.techdesk.techdeskmobile.Model;

// Model - Responsável por armazenar dados de criação de conta
public class CriarContaModel {
    private String nome;
    private String email;
    private String senha;

    // Construtor
    public CriarContaModel(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método para simular a criação da conta
    public boolean criarConta() {
        // Simulação de validação
        if (nome != null && email != null && senha != null) {
            return true;  // Conta criada com sucesso
        }
        return false;  // Falha ao criar conta
    }
}
