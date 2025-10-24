package com.techdesk.techdeskmobile.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.View.TelaEntrarTD;
import com.techdesk.techdeskmobile.View.TelaCriarContaTD;
import com.techdesk.techdeskmobile.View.TermosPrivacidade;

public class TelaLoginCadController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ativa o modo Edge-to-Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda_tela);

        // Ajuste de padding para status/navigation bars
        View mainLayout = findViewById(R.id.main);
        if (mainLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Botão "Entrar"
        Button buttonEntrar = findViewById(R.id.button2);
        buttonEntrar.setOnClickListener(v -> irParaTelaEntrar());

        // Botão "Crie sua Conta"
        Button buttonCriarConta = findViewById(R.id.button3);
        buttonCriarConta.setOnClickListener(v -> irParaTelaCriarConta());

        // TextView "Termos de Serviço e Privacidade"
        TextView textTermos = findViewById(R.id.textView2);
        if (textTermos != null) {
            textTermos.setOnClickListener(v -> irParaTermos());
        } else {
            Log.e("TelaLoginCadController", "TextView textView2 (Termos de Privacidade) não encontrado!");
        }
    }

    // Navegação para a tela de login
    private void irParaTelaEntrar() {
        Intent intent = new Intent(TelaLoginCadController.this, TelaEntrarTD.class);
        startActivity(intent);
    }

    // Navegação para a tela de criar conta
    private void irParaTelaCriarConta() {
        Intent intent = new Intent(TelaLoginCadController.this, TelaCriarContaTD.class);
        startActivity(intent);
    }

    // Navegação para acessar os Termos de Privacidade
    private void irParaTermos() {
        Intent intent = new Intent(TelaLoginCadController.this, TermosPrivacidade.class);
        startActivity(intent);
    }
}
