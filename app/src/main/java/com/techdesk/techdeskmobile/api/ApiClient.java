package com.techdesk.techdeskmobile.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Classe responsável pela configuração do Retrofit.
// Esta classe funciona como parte da camada de comunicação com a API. Aqui criei o cliente HTTP usado em todas as requisições.
// Autor: Pedro Pestana
public class ApiClient {

    // URL base do back-end. Durante o desenvolvimento utilizamos o IP da máquina local
    // Na API foi utilizado localdb, mas no mobile tive problemas passando localhost, com isso coloquei o IP da maquina. Pego com o ipconfig no cmd.
    private static final String BASE_URL = "http://192.168.0.7:7093/";

    private static Retrofit retrofit;

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }
        return retrofit.create(ApiService.class);
    }
    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }
        return retrofit;
    }

    // Criação do objeto Retrofit com.. LoggingInterceptor para depuração
    // Gson configurado para interpretar JSON de forma flexível
    // Tivemos alguns problemas com Gson, para isso verifiquei algumas docs
    // https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.5/com/google/gson/GsonBuilder.html
    private static Retrofit createRetrofit() {

        // Interceptor para exibir as requisições e respostas no Logcat - Utilizado Logcat para os testes e encontrar os erros..
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Cliente HTTP configurado com o interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        // Configuração básica do Gson
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
