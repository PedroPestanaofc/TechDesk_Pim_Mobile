// View Criada para mostrar a interface para o Usuario, Tela onde pergunta ao
// usuario se ele quer entrar ou cadastrar
// autor: Pedro Pestana
// Data: 15 de Junho de 2025

package com.techdesk.techdeskmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.R;

public class ActivityTelaUsuario extends AppCompatActivity {

    private Button btnLogin;
    private Button btnCriarConta;
    private TextView txtTermos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        inicializarComponentes();
        configurarListeners();
    }

    private void inicializarComponentes() {
        btnLogin = findViewById(R.id.button2);
        btnCriarConta = findViewById(R.id.button3);
    }

    private void configurarListeners() {
        // Botão de Login -> direciona para ActivityTelaEntrarTD
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityTelaUsuario.this, ActivityTelaEntrarTD.class);
            startActivity(intent);
        });

        // Botão de Criar Conta -> direciona para ActivityTelaCriarContaTD
        btnCriarConta.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityTelaUsuario.this, ActivityTelaCriarContaTD.class);
            startActivity(intent);
        });

    }
}
