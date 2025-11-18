package com.techdesk.techdeskmobile.model;

import com.google.gson.annotations.SerializedName;

public class MensagemChamado {

    private int id;

    @SerializedName("idChamado")
    private int idChamado;

    @SerializedName("usuarioId")
    private int idUsuario;

    @SerializedName("descricao")
    private String mensagem;   // <- descrição é a mensagem

    @SerializedName("data")
    private String dataHora;   // <- campo data

    // Se NÃO existe autor no JSON, remova ou coloque valor padrão
    private String autor = "Usuário";

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdChamado() { return idChamado; }
    public void setIdChamado(int idChamado) { this.idChamado = idChamado; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
}
