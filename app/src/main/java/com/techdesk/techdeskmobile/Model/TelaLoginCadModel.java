package com.techdesk.techdeskmobile.Model;

//-------------------------------------------------------------------
// Classe: TelaLoginCadModel
// Descrição: Model criada para armazenar a informação de login e cadastro
// @author Pedro Pestana
// @company TechDesk
// @since 09/10/2025
// @version 1.0
//-------------------------------------------------------------------
public class TelaLoginCadModel {

    private String usuario;
    private String senha;

    // Construtor
    public TelaLoginCadModel(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    // Getters e setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
