package com.techdesk.techdeskmobile.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.Model.Chamado;
import com.techdesk.techdeskmobile.R;

public class DetalhesChamadoController extends AppCompatActivity {

    private TextView textViewId, textViewTitulo, textViewResponsavel, textViewPrioridade, textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_chamado);

        // Inicializa os TextViews onde vamos exibir os detalhes
        textViewId = findViewById(R.id.textViewId);
        textViewTitulo = findViewById(R.id.textViewTitulo);
        textViewResponsavel = findViewById(R.id.textViewResponsavel);
        textViewPrioridade = findViewById(R.id.textViewPrioridade);
        textViewStatus = findViewById(R.id.textViewStatus);

        // Obtém os dados passados pela Intent
        String id = getIntent().getStringExtra("ID");
        String titulo = getIntent().getStringExtra("TITULO");
        String responsavel = getIntent().getStringExtra("RESPONSAVEL");
        String prioridade = getIntent().getStringExtra("PRIORIDADE");
        String status = getIntent().getStringExtra("STATUS");

        // Cria um objeto Chamado com os dados recebidos
        Chamado chamado = new Chamado(id, titulo, responsavel, prioridade, status);

        // Passa os dados do chamado para os TextViews
        mostrarDetalhes(chamado);
    }

    // Metodo para exibir os detalhes do chamado nos TextViews
    private void mostrarDetalhes(Chamado chamado) {
        textViewId.setText("ID: " + chamado.getId());
        textViewTitulo.setText("Título: " + chamado.getTitulo());
        textViewResponsavel.setText("Responsável: " + chamado.getResponsavel());
        textViewPrioridade.setText("Prioridade: " + chamado.getPrioridade());
        textViewStatus.setText("Status: " + chamado.getStatus());
    }
}
