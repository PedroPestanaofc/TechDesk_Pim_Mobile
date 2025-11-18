package com.techdesk.techdeskmobile.controller;

import android.content.Context;

import com.techdesk.techdeskmobile.model.MensagemChamado;
import com.techdesk.techdeskmobile.api.ApiClient;
import com.techdesk.techdeskmobile.api.ApiMensagem;
import com.techdesk.techdeskmobile.util.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Controller responsável pela comunicação com a API de Mensagens.
// Segue o padrão MVC para manter a View desacoplada da lógica de rede.
// Autor: Pedro Pestana
public class MensagemController {

    private final ApiMensagem apiMensagem;
    private final SessionManager session;

    public MensagemController(Context context) {
        this.apiMensagem = ApiClient.getClient(context).create(ApiMensagem.class);
        this.session = new SessionManager(context);
    }

    // Metodo responsável por buscar o histórico de mensagens de um chamado
    public void listarMensagensPorChamado(int idChamado, Callback<List<MensagemChamado>> callback) {
        if (idChamado <= 0) return;

        Call<List<MensagemChamado>> call = apiMensagem.getMensagensPorChamado(idChamado);
        call.enqueue(callback);
    }

    // Envia uma nova mensagem para o chamado
    public void enviarMensagem(int idChamado, String texto, Callback<MensagemChamado> callback) {

        if (idChamado <= 0 || texto == null || texto.trim().isEmpty())
            return;

        MensagemChamado msg = new MensagemChamado();
        msg.setIdChamado(idChamado);
        msg.setIdUsuario(session.getUserId());
        msg.setMensagem(texto);

        // Envio da mensagem para a API
        Call<MensagemChamado> call = apiMensagem.criarMensagem(idChamado, msg);

        call.enqueue(new Callback<MensagemChamado>() {
            @Override
            public void onResponse(Call<MensagemChamado> call, Response<MensagemChamado> response) {

                callback.onResponse(call, response);

                // Após enviar, atualiza automaticamente o histórico
                listarMensagensPorChamado(idChamado, new Callback<List<MensagemChamado>>() {
                    @Override
                    public void onResponse(Call<List<MensagemChamado>> call,
                                           Response<List<MensagemChamado>> response) {
                    }

                    @Override
                    public void onFailure(Call<List<MensagemChamado>> call, Throwable t) {
                    }
                });
            }

            @Override
            public void onFailure(Call<MensagemChamado> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
