package com.techdesk.techdeskmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.controller.ChamadoController;
import com.techdesk.techdeskmobile.model.Chamado;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// View responsável por exibir a tela de abertura de chamados.
// Aqui o usuário digita as informações e envia para o Controller.
// Autor: Pedro Pestana

public class ActivityTelaAbrirChamados extends AppCompatActivity {

    private EditText etTitulo, etDescricao;
    private Spinner spinnerCategoria;
    private Button btnAbrirChamado, btnSair;

    private ChamadoController chamadoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abrir_chamados);

        inicializarComponentes();

        chamadoController = new ChamadoController(this);

        // Evento de clique do botão abrir chamado
        btnAbrirChamado.setOnClickListener(v -> abrirChamado());

        // Botão sair simplesmente fecha a Activity
        btnSair.setOnClickListener(v -> finish());
    }
    private void inicializarComponentes() {
        etTitulo = findViewById(R.id.textInputEditText2);
        etDescricao = findViewById(R.id.textInputEditText);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        btnAbrirChamado = findViewById(R.id.buttonAbrir);
        btnSair = findViewById(R.id.buttonSair);
    }

    //Metodo chamado ao clicar no botão "Abrir Chamado". Vai coleta os dados preenchidos na abertura do chamado, fazer a validação e chama a Controller.

    private void abrirChamado() {
        String titulo = etTitulo.getText().toString().trim();
        String descricao = etDescricao.getText().toString().trim();
        String categoriaSelecionada = spinnerCategoria.getSelectedItem().toString();

        // Validação simples para garantir preenchimento mínimo com os campos obrigatórios pela api.
        if (titulo.isEmpty() || descricao.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Converte nome da categoria para ID
        int idCategoria = obterIdCategoria(categoriaSelecionada);

        btnAbrirChamado.setEnabled(false);

        // Chama a controller para abrir o chamado
        chamadoController.abrirChamado(titulo, descricao, idCategoria, new Callback<Chamado>() {
            @Override
            public void onResponse(Call<Chamado> call, Response<Chamado> response) {
                btnAbrirChamado.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    Chamado chamadoCriado = response.body();

                    Toast.makeText(ActivityTelaAbrirChamados.this,
                            "Chamado aberto com sucesso!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ActivityTelaAbrirChamados.this, ActivityTelaPrincipal.class);
                    intent.putExtra("chamadoId", chamadoCriado.getIdChamado());
                    startActivity(intent);

                    limparCampos();
                } else {
                    Toast.makeText(ActivityTelaAbrirChamados.this,
                            "Erro ao abrir chamado. Código: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Chamado> call, Throwable t) {
                btnAbrirChamado.setEnabled(true);
                Toast.makeText(ActivityTelaAbrirChamados.this,
                        "Falha na comunicação: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    // Reseta os campos após a criação do chamado.

    private void limparCampos() {
        etTitulo.setText("");
        etDescricao.setText("");
        spinnerCategoria.setSelection(0);
    }

    //Converte o nome da categoria para o ID.

    private int obterIdCategoria(String nomeCategoria) {
        switch (nomeCategoria) {
            case "Hardware": return 1;
            case "Software": return 2;
            case "Rede": return 3;
            case "Outros": return 4;
            default: return 0;
        }
    }
}
