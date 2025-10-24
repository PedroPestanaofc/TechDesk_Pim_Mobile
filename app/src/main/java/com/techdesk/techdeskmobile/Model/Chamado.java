package com.techdesk.techdeskmobile.Model;

import android.net.Uri;

public class Chamado {
    private String id;
    private String titulo;
    private String categoria;
    private String prioridade;
    private String status;
    private String responsavel;
    private Uri imagemAnexo; // Adicionando o campo para o anexo (se necessário)

    // Construtor
    public Chamado(String id, String titulo, String categoria, String prioridade, String status, String responsavel, Uri imagemAnexo) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.status = status;
        this.responsavel = responsavel;
        this.imagemAnexo = imagemAnexo;
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Uri getImagemAnexo() {
        return imagemAnexo;
    }

    public void setImagemAnexo(Uri imagemAnexo) {
        this.imagemAnexo = imagemAnexo;
    }
}
