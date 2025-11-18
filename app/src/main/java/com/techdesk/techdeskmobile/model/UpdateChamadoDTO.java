package com.techdesk.techdeskmobile.model;

import com.google.gson.annotations.SerializedName;

// DTO usado para atualizar o Chamado via PUT
public class UpdateChamadoDTO {

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("status")
    private String status;

    @SerializedName("nivel")
    private String nivel;

    @SerializedName("dataFinal")
    private String dataFinal;

    @SerializedName("idUsuario")
    private Integer idUsuario;

    @SerializedName("idCategoria")
    private Integer idCategoria;

    @SerializedName("idTecnico")
    private Integer idTecnico;

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getDataFinal() { return dataFinal; }
    public void setDataFinal(String dataFinal) { this.dataFinal = dataFinal; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }

    public Integer getIdTecnico() { return idTecnico; }
    public void setIdTecnico(Integer idTecnico) { this.idTecnico = idTecnico; }
}
