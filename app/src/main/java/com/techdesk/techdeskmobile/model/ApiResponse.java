/*
+======================================================================================+
| Classe: ApiResponse<T>                                                                |
| Autor: Pedro Pestana                                                                  |
| Data: 11 de Novembro de 2025                                                          |
| Função: Padronizar a resposta da API para o aplicativo.                               |
|                                                                                       |
| Explicação (pensando como estudante de ADS):                                          |
| Esta classe foi criada porque diferentes endpoints do backend podem retornar         |
| respostas com nomes de campos diferentes. Para evitar criar vários modelos           |
| diferentes, usamos uma classe genérica (ApiResponse<T>) capaz de mapear qualquer     |
| tipo de dado retornado pela API.                                                      |
|                                                                                       |
| Dentro da arquitetura MVC:                                                            |
| → Esta classe pertence ao "MODEL", pois representa a estrutura dos dados que         |
|   recebemos da API e será utilizada pelos Controllers para interpretar a resposta.   |
+======================================================================================+
*/

package com.techdesk.techdeskmobile.model;

import com.google.gson.annotations.SerializedName;
// Classe: ApiResponse
// Criada para padronizar as respostas da API para o aplicativo.
// Esta classe foi desenvolvida para unificar o tratamento das respostas da API, já que diferentes endpoints podem retornar dados com nomes de campos distintos.
// Ao invés de criar modelos específicos para cada endpoint, optamos por uma abordagem genérica utilizando a classe ApiResponse<T>,
// que permite mapear e manipular qualquer tipo de dado retornado pela API de forma flexível e reutilizável.
// Autor: Pedro Pestana

public class ApiResponse<T> {

    @SerializedName(value = "success", alternate = {"sucesso"})
    private boolean success;

    @SerializedName(value = "message", alternate = {"mensagem"})
    private String message;

    @SerializedName(value = "data", alternate = {"dados", "result", "resultado"})
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Caso a mensagem venha nula, devolvemos uma string vazia para evitar NullPointerException
    public String getMessage() {
        return message != null ? message : "";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Metodo auxiliar para verificar se a API realmente retornou algum dado
    public boolean hasData() {
        return data != null;
    }

    // Sobrescrita do toString() para facilitar depuração e logs durante o desenvolvimento
    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + (data != null ? data.getClass().getSimpleName() : "null") +
                '}';
    }
}
