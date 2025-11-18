package com.techdesk.techdeskmobile.model;

import com.google.gson.annotations.SerializedName;

// Model que representa o usuário esperado pela API.
// Contém todos os atributos referentes ao cadastro do usuário.
// Autor: Pedro Pestana
public class Usuario {

    private int id;
    private String nome;
    private String email;

    // O backend envia "senhaHash", por isso a anotação SerializedName
    @SerializedName("senhaHash")
    private String senha;

    private String perfil;
    private Boolean ativo;
    private String criadoEm;
    private String atualizadoEm;

    // Construtor completo
    public Usuario(int id, String nome, String email, String senha,
                   String perfil, Boolean ativo, String criadoEm, String atualizadoEm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    // Construtor vazio — útil para casos de deserialização automática
    public Usuario() { }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenhaHash() { return senha; }
    public String getPerfil() { return perfil; }
    public Boolean isAtivo() { return ativo; }
    public String getCriadoEm() { return criadoEm; }
    public String getAtualizadoEm() { return atualizadoEm; }

    // Setters - Um metodo publico que define um novo valor para um atributo privado.
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenhaHash(String senhaHash) { this.senha = senhaHash; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public void setCriadoEm(String criadoEm) { this.criadoEm = criadoEm; }
    public void setAtualizadoEm(String atualizadoEm) { this.atualizadoEm = atualizadoEm; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senhaHash='" + senha + '\'' +
                ", perfil='" + perfil + '\'' +
                ", ativo=" + ativo +
                ", criadoEm='" + criadoEm + '\'' +
                ", atualizadoEm='" + atualizadoEm + '\'' +
                '}';
    }
}
