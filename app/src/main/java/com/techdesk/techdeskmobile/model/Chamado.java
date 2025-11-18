package com.techdesk.techdeskmobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Classe de modelo que representa um Chamado no sistema.
// Mapeia exatamente os dados recebidos (GET) / enviados (POST) via API (JSON).

public class Chamado {

    // ID gerado pelo backend (apenas leitura)
    @SerializedName("idChamado")
    @Expose(serialize = false, deserialize = true)
    private Integer idChamado;

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("status")
    private String status;

    @SerializedName("nivel")
    private String nivel;

    // Prioridade do chamado (Alta, Média, Baixa)
    // Adicionado porque será enviado/recebido pela API na v02
    @SerializedName("prioridade")
    private String prioridade;

    // Datas
    @SerializedName("dataInicio")
    private String dataInicio;

    @SerializedName("dataFinal")
    private String dataFinal;

    @SerializedName("usuarioNome")
    private String usuarioNome;

    @SerializedName("categoriaNome")
    private String categoriaNome;

    @SerializedName("tecnicoNome")
    private String tecnicoNome;

    @SerializedName("idUsuario")
    private Integer idUsuario;

    @SerializedName("idCategoria")
    private Integer idCategoria;

    @SerializedName("idTecnico")
    private Integer idTecnico;

    // Getters e Setters
    public Integer getIdChamado() { return idChamado; }
    public void setIdChamado(Integer idChamado) { this.idChamado = idChamado; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFinal() { return dataFinal; }
    public void setDataFinal(String dataFinal) { this.dataFinal = dataFinal; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    public String getCategoriaNome() { return categoriaNome; }
    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }

    public String getTecnicoNome() { return tecnicoNome; }
    public void setTecnicoNome(String tecnicoNome) { this.tecnicoNome = tecnicoNome; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }

    public Integer getIdTecnico() { return idTecnico; }
    public void setIdTecnico(Integer idTecnico) { this.idTecnico = idTecnico; }

    // Métodos simples para verificar status do chamado
    public boolean isAberto() {
        return "Aberto".equalsIgnoreCase(status);
    }

    public boolean isEmAndamento() {
        return "Em andamento".equalsIgnoreCase(status);
    }

    public boolean isFechado() {
        return "Fechado".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return "Chamado{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                ", nivel='" + nivel + '\'' +
                ", prioridade='" + prioridade + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFinal='" + dataFinal + '\'' +
                ", usuarioNome='" + usuarioNome + '\'' +
                ", categoriaNome='" + categoriaNome + '\'' +
                ", tecnicoNome='" + tecnicoNome + '\'' +
                ", idUsuario=" + idUsuario +
                ", idCategoria=" + idCategoria +
                ", idTecnico=" + idTecnico +
                '}';
    }
}
