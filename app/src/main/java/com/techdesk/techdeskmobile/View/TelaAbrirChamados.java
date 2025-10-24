package com.techdesk.techdeskmobile.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.Controller.ChamadoController;
import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.TelaPrincipal;

public class TelaAbrirChamados extends AppCompatActivity {

    private ActivityResultLauncher<Intent> filePickerLauncher;
    private Uri imagemSelecionada = null; // Para armazenar a URI da imagem selecionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abrir_chamados);

        // Botão "Sair" - volta para login
        Button btnSair = findViewById(R.id.buttonSair);
        btnSair.setOnClickListener(v -> {
            Intent intent = new Intent(TelaAbrirChamados.this, TelaPrincipal.class);
            startActivity(intent);
            finish();
        });

        // Botão "Abrir Chamado" - cria o chamado com os dados e passa para o Controller
        Button btnAbrirChamado = findViewById(R.id.buttonAbrir);
        btnAbrirChamado.setOnClickListener(v -> {
            // Dados do chamado (você pode pegar de campos de texto na UI, como EditText)
            String titulo = "Problema no sistema"; // Exemplo
            String categoria = "Erro Técnico"; // Exemplo
            String prioridade = "Alta"; // Exemplo
            String status = "Novo"; // Exemplo
            String responsavel = "Carlos Silva"; // Exemplo de responsável

            // Chamando o Controller para abrir o chamado
            ChamadoController.abrirChamado(TelaAbrirChamados.this, titulo, categoria, prioridade, status, responsavel, imagemSelecionada);
        });

        // Seleção de imagem para anexo
        TextView txtAnexo = findViewById(R.id.txtAnexo);
        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        imagemSelecionada = result.getData().getData(); // Armazenando o URI da imagem
                        Toast.makeText(this, "Imagem selecionada: " + imagemSelecionada.getLastPathSegment(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        txtAnexo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            filePickerLauncher.launch(intent);
        });
    }
}
