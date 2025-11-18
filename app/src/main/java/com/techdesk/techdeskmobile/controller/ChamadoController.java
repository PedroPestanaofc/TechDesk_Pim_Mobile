package com.techdesk.techdeskmobile.controller;

import android.content.Context;

import com.techdesk.techdeskmobile.model.Chamado;
import com.techdesk.techdeskmobile.api.ApiClient;
import com.techdesk.techdeskmobile.api.ApiService;
import com.techdesk.techdeskmobile.model.UpdateChamadoDTO;
import com.techdesk.techdeskmobile.util.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Controller responsável por toda a lógica de negócio relacionada aos chamados
// Autor: Pedro Pestana
public class ChamadoController {

    private final ApiService apiService;
    private final SessionManager sessionManager;

    public ChamadoController(Context context) {
        this.apiService = ApiClient.getApiService();
        this.sessionManager = SessionManager.getInstance(context);
    }

    public void listarChamados(String status, Callback<List<Chamado>> callback) {
        int userId = sessionManager.getUserId();

        if (userId == -1) {
            callback.onFailure(null, new Throwable("Usuário não logado"));
            return;
        }

        Call<List<Chamado>> call = apiService.listarChamados();

        call.enqueue(new Callback<List<Chamado>>() {
            @Override
            public void onResponse(Call<List<Chamado>> call, Response<List<Chamado>> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    callback.onFailure(call, new Throwable("Erro ao buscar chamados"));
                    return;
                }

                List<Chamado> todosChamados = response.body();

                // Filtrar chamados do usuário logado
                todosChamados.removeIf(c ->
                        c.getIdUsuario() == null || !c.getIdUsuario().equals(userId)
                );

                // Filtro por status (opcional)
                if (status != null && !status.equalsIgnoreCase("Todos")) {

                    todosChamados.removeIf(c -> {
                        String statusReal = normalizarStatus(c.getStatus());
                        return statusReal == null || !statusReal.equalsIgnoreCase(status);
                    });
                }

                callback.onResponse(call, Response.success(todosChamados));
            }

            @Override
            public void onFailure(Call<List<Chamado>> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    // Normaliza o status vindo da API
    private String normalizarStatus(String status) {
        if (status == null) return null;

        switch (status.toLowerCase()) {
            case "aberto":
                return "Aberto";
            case "emandamento":
            case "em andamento":
                return "Em Andamento";
            case "fechado":
                return "Fechado";
            default:
                return status;
        }
    }


    // ABRIR CHAMADO
    public void abrirChamado(String titulo, String descricao, int idCategoria, Callback<Chamado> callback) {
        int userId = sessionManager.getUserId();

        if (userId == -1) {
            callback.onFailure(null, new Throwable("Usuário não logado"));
            return;
        }

        Chamado chamado = new Chamado();
        chamado.setTitulo(titulo);
        chamado.setDescricao(descricao);
        chamado.setIdUsuario(userId);
        chamado.setIdCategoria(idCategoria);
        chamado.setStatus("Aberto");
        chamado.setNivel("N1");
        chamado.setIdTecnico(4);
        chamado.setDataInicio(getDataAtual());

        apiService.abrirChamado(chamado).enqueue(callback);
    }


    // FINALIZAR CHAMADO
    public void finalizarChamado(int idChamado, Callback<Void> callback) {
        apiService.getChamado(idChamado).enqueue(new Callback<Chamado>() {
            @Override
            public void onResponse(Call<Chamado> call, Response<Chamado> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    callback.onFailure(null, new Throwable("Falha ao carregar chamado para finalizar"));
                    return;
                }

                Chamado ch = response.body();

                UpdateChamadoDTO dto = new UpdateChamadoDTO();
                dto.setTitulo(ch.getTitulo());
                dto.setDescricao(ch.getDescricao());
                dto.setIdUsuario(ch.getIdUsuario());
                dto.setIdCategoria(ch.getIdCategoria());
                dto.setIdTecnico(ch.getIdTecnico());
                dto.setNivel(ch.getNivel());
                dto.setStatus("Fechado");
                dto.setDataFinal(getDataAtual());

                apiService.finalizarChamado(idChamado, dto).enqueue(callback);
            }

            @Override
            public void onFailure(Call<Chamado> call, Throwable t) {
                callback.onFailure(null, t);
            }
        });
    }

    public void listarChamado(int idChamado, Callback<Chamado> callback) {
        apiService.getChamado(idChamado).enqueue(callback);
    }

    private String getDataAtual() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                .format(new Date());
    }
}
