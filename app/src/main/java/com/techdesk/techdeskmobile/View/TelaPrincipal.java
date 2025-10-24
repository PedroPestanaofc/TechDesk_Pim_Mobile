package com.techdesk.techdeskmobile.View;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.Controller.TelaPrincipalController;
import com.techdesk.techdeskmobile.R;

public class TelaPrincipal extends AppCompatActivity {

    private TelaPrincipalController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        controller = new TelaPrincipalController(this);

        // Botão "Abrir Chamados"
        Button btnAbrirChamado = findViewById(R.id.button11);
        if (btnAbrirChamado != null) {
            btnAbrirChamado.setOnClickListener(v -> controller.onAbrirChamadoClicked());
        }

        // Botão "Meus Chamados" - histórico
        Button btnHistorico = findViewById(R.id.button12);
        if (btnHistorico != null) {
            btnHistorico.setOnClickListener(v -> controller.onHistoricoClicked());
        }

        // Botão "Falar com a IA - TechBot"
        Button btnIA = findViewById(R.id.button17);
        if (btnIA != null) {
            btnIA.setOnClickListener(v -> controller.onFalarComIAClicked());
        }

        // Botão "Sair"
        Button btnSair = findViewById(R.id.button15);
        if (btnSair != null) {
            btnSair.setOnClickListener(v -> controller.onSairClicked());
        }
    }
}
