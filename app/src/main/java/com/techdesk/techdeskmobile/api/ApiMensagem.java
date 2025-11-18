package com.techdesk.techdeskmobile.api;

import com.techdesk.techdeskmobile.model.MensagemChamado;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

// Interface responsável por definir os endpoints consumidos pelo aplicativo.
// Esta interface faz parte do Model (comunicação com API).
// Cada metodo representa um endpoint do backend.
// Autor: Pedro Pestana
public interface ApiMensagem {

    // Fazer a busca das mensagens pelo chamado
    @GET("api/Mensagem/chamado/{idChamado}")
    Call<List<MensagemChamado>> getMensagensPorChamado(@Path("idChamado") int chamadoId);

    // Inclusão de uma nova mensagem
    @POST("api/Mensagem/{chamadoId}")
    Call<MensagemChamado> criarMensagem(
            @Path("chamadoId") int idChamado,
            @Body MensagemChamado mensagem
    );
}
