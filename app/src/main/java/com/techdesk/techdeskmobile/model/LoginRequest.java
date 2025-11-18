package com.techdesk.techdeskmobile.model;

import com.google.gson.annotations.SerializedName;

// Model de requisição de login.
// Esta classe é utilizada exclusivamente para enviar dados ao backend.
// Autor: Pedro Pestana
public class LoginRequest {

    private String email;

    // O backend criado espera o campo "senha" com o hash (senhaHash)
    @SerializedName("senha")
    private String senha;

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() { return email; }
    public String getSenha() { return senha; }
}
