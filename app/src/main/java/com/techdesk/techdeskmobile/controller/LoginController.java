package com.techdesk.techdeskmobile.controller;

import android.content.Context;
import android.util.Log;

import com.techdesk.techdeskmobile.model.LoginRequest;
import com.techdesk.techdeskmobile.model.Usuario;
import com.techdesk.techdeskmobile.api.ApiClient;
import com.techdesk.techdeskmobile.api.ApiService;
import com.techdesk.techdeskmobile.util.SessionManager;
import com.techdesk.techdeskmobile.util.Utils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Controller responsável pela lógica de autenticação do usuário.
// Autor: Pedro Pestana
public class LoginController {

    private static final String TAG = "LoginController";

    private final ApiService apiService;
    private final SessionManager sessionManager;

    public LoginController(Context context) {
        this.apiService = ApiClient.getApiService();
        this.sessionManager = new SessionManager(context);
    }

    //Realiza login do usuário, Aqui é aplicada a regra de negócio: converter a senha para MD5 antes do envio.

    public void login(String email, String senhaRaw, LoginCallback callback) {

        if (email == null || senhaRaw == null) {
            callback.onError("Dados inválidos");
            return;
        }

        // Converte senha para HASH usando metodo da aplicação
        String senhaHash = Utils.md5(senhaRaw.trim());
        if (senhaHash == null) {
            callback.onError("Erro ao calcular hash da senha");
            return;
        }

        LoginRequest request = new LoginRequest(email.trim(), senhaHash);

        Log.d(TAG, "LoginRequest prepared: email=" + request.getEmail());

        Call<Usuario> call = apiService.login(request);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario = response.body();

                    // Armazena sessão básica do usuário
                    sessionManager.saveSession(usuario.getId(), usuario.getEmail());

                    Log.d(TAG, "Login OK - user: " + usuario.getNome());
                    callback.onSuccess(usuario);
                    return;
                }

                // Tratamento de erro detalhado (didático)
                String errorMsg = "Usuário ou senha inválidos";

                try {
                    if (response.errorBody() != null) {
                        String raw = response.errorBody().string();
                        if (raw != null && !raw.trim().isEmpty()) {
                            errorMsg = raw;
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Erro lendo errorBody", e);
                    errorMsg = "Erro ao processar resposta do servidor";
                }

                callback.onError(errorMsg);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                callback.onError("Erro de conexão: " + t.getMessage());
            }
        });
    }

    // Interface de retorno (callback assíncrono)
    public interface LoginCallback {
        void onSuccess(Usuario usuario);
        void onError(String mensagem);
    }
}
