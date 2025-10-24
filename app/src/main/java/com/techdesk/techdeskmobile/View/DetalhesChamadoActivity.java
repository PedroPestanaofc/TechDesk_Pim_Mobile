package com.techdesk.techdeskmobile.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.R;

public class DetalhesChamadoActivity extends AppCompatActivity {

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
    }

    // Metodo para exibir os detalhes do chamado
    public void exibirDetalhes(String id, String titulo, String responsavel, String prioridade, String status) {
        textViewId.setText("ID: " + id);
        textViewTitulo.setText("Título: " + titulo);
        textViewResponsavel.setText("Responsável: " + responsavel);
        textViewPrioridade.setText("Prioridade: " + prioridade);
        textViewStatus.setText("Status: " + status);
    }
}
