package com.techdesk.techdeskmobile.api;

import com.techdesk.techdeskmobile.model.Chamado;
import com.techdesk.techdeskmobile.model.LoginRequest;
import com.techdesk.techdeskmobile.model.UpdateChamadoDTO;
import com.techdesk.techdeskmobile.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

// Interface responsável por definir os endpoints consumidos pelo aplicativo.
// Esta interface faz parte do Model (comunicação com API).
// Cada metodo representa um endpoint do backend.
// Autor: Pedro Pestana

public interface ApiService {

    // Endpoint para criação de conta
    @POST("api/usuarios")
    Call<Usuario> criarConta(@Body Usuario usuario);

    // Endpoint utilizado na autenticação do usuário
    @POST("api/Login")
    Call<Usuario> login(@Body LoginRequest request);

    // Abre um novo chamado no sistema
    @POST("api/chamados")
    Call<Chamado> abrirChamado(@Body Chamado chamado);

    // Retorna um chamado específico pelo id
    @GET("api/chamados/{id}")
    Call<Chamado> getChamado(@Path("id") int id);

    // Lista todos os chamados para o usuário logado
    @GET("api/chamados")
    Call<List<Chamado>> listarChamados();

    // FINALIZAR CHAMADO
    @PUT("api/chamados/{id}")
    Call<Void> finalizarChamado(@Path("id") int id, @Body UpdateChamadoDTO dto);
}
