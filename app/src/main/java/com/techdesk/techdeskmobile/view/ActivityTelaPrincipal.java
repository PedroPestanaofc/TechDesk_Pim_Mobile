package com.techdesk.techdeskmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.MainActivity;
import com.techdesk.techdeskmobile.R;

public class ActivityTelaPrincipal extends AppCompatActivity {

    private Button btnMeusChamados, btnAbrirChamado, btnSair;
    private TextView txtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        btnMeusChamados = findViewById(R.id.button12);
        btnAbrirChamado  = findViewById(R.id.button11);
        btnSair          = findViewById(R.id.button15);
        txtUsuario       = findViewById(R.id.textView6);

        // Receber nome via Intent, função não implementada 100%, finalizar na v02..
        String nome = getIntent().getStringExtra("nomeUsuario");
        txtUsuario.setText(nome != null ? "Olá, " + nome + "!" : "Olá, Usuário!");

        // Abrir “Meus Chamados”
        btnMeusChamados.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(ActivityTelaPrincipal.this, ActivityMeusChamados.class);
                startActivity(i);
            }
        });

        // Abrir “Abrir Chamados”
        btnAbrirChamado.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(ActivityTelaPrincipal.this, ActivityTelaAbrirChamados.class);
                startActivity(i);
            }
        });

        // Sair → voltar ao início
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(ActivityTelaPrincipal.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}
