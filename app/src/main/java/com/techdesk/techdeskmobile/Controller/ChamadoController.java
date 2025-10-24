package com.techdesk.techdeskmobile.Controller;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.techdesk.techdeskmobile.Model.Chamado;

public class ChamadoController {

    // Metodo para criar e enviar o chamado
    public static void abrirChamado(Context context, String titulo, String categoria, String prioridade, String status, String responsavel, Uri imagemAnexo) {
        // Criar o objeto Chamado
        Chamado chamado = new Chamado("123", titulo, categoria, prioridade, status, responsavel, imagemAnexo);

        // Aqui você pode implementar a lógica de envio do chamado, como salvar em um banco de dados ou API
        // Por enquanto, simulando o envio com um Toast
        Toast.makeText(context, "Chamado " + chamado.getTitulo() + " enviado", Toast.LENGTH_SHORT).show();

        // Se quiser realmente enviar o chamado para um servidor ou outro lugar, implemente essa lógica aqui.
    }
}
