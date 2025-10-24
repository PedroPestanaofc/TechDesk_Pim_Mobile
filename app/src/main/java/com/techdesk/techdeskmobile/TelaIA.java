package com.techdesk.techdeskmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class TelaIA extends AppCompatActivity {

    private static final int PICK_FILE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_ia);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Componentes
        Button btnVoltar = findViewById(R.id.button);
        Button btnPerguntar = findViewById(R.id.button32);
        TextView txtResposta = findViewById(R.id.txtResposta);
        TextInputEditText editText = findViewById(R.id.editText);

        // Botão Voltar
        btnVoltar.setOnClickListener(v -> finish());

        // Botão Perguntar
        btnPerguntar.setOnClickListener(v -> {
            String pergunta = editText.getText().toString().trim();
            if (pergunta.isEmpty()) {
                Toast.makeText(this, "Digite uma pergunta antes de enviar", Toast.LENGTH_SHORT).show();
            } else {
                txtResposta.setText("🤖 TechBot: ótima pergunta! Em breve terei uma resposta mais completa para: " + pergunta);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            Toast.makeText(this, "Arquivo selecionado: " + uri.getLastPathSegment(), Toast.LENGTH_SHORT).show();
        }
    }
}
