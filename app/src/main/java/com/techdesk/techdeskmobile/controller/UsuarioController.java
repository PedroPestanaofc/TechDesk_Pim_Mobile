package com.techdesk.techdeskmobile.controller;

import com.techdesk.techdeskmobile.model.Usuario;
import com.techdesk.techdeskmobile.api.ApiClient;
import com.techdesk.techdeskmobile.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Controller responsável por operações relacionadas ao usuário.
// Implementa a comunicação com a API para criação de conta.
// Autor: Pedro Pestana
public class UsuarioController {

    private ApiService apiService;

    public UsuarioController() {
        apiService = ApiClient.getApiService();
    }

    //Envia para o backend uma requisição para criar um novo usuário.  É uma operação assíncrona via Retrofit.
    public void criarConta(Usuario usuario, Callback<Usuario> callback) {

        Call<Usuario> call = apiService.criarConta(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    callback.onFailure(call, new Throwable("Erro na criação de conta"));
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
