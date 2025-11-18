package com.techdesk.techdeskmobile.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.controller.ChamadoController;
import com.techdesk.techdeskmobile.controller.MensagemController;
import com.techdesk.techdeskmobile.model.MensagemChamado;
import com.techdesk.techdeskmobile.util.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// View responsável por exibir a interface dos detalhes do chamado para o usuário.
// Autor: Pedro Pestana

public class ActivityDetalhesChamados extends AppCompatActivity {

    private RecyclerView recyclerViewMensagens;
    private EditText editTextNovaMensagem, editTextDescricao;
    private Button buttonEnviarMensagem, buttonVoltar, buttonFinalizarChamado;
    private TextView textViewTitulo, textViewCategoria, textViewPrioridade, textViewStatus;

    private MensagemController mensagemController;
    private ChamadoController chamadoController;

    private MensagemAdapter adapter;
    private List<MensagemChamado> listaMensagens = new ArrayList<>();

    private SessionManager session;
    private int chamadoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_chamado);

        recyclerViewMensagens = findViewById(R.id.recyclerViewMensagens);
        editTextNovaMensagem = findViewById(R.id.editTextNovaMensagem);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        buttonEnviarMensagem = findViewById(R.id.buttonEnviarMensagem);
        buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonFinalizarChamado = findViewById(R.id.buttonFinalizarChamado);
        textViewTitulo = findViewById(R.id.textViewTitulo);
        textViewCategoria = findViewById(R.id.textViewCategoria);
        textViewPrioridade = findViewById(R.id.textViewPrioridade);
        textViewStatus = findViewById(R.id.textViewStatus);

        mensagemController = new MensagemController(this);
        chamadoController = new ChamadoController(this);
        session = new SessionManager(this);

        recyclerViewMensagens.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MensagemAdapter(this, listaMensagens);
        recyclerViewMensagens.setAdapter(adapter);

        chamadoId = getIntent().getIntExtra("chamadoId", 0);
        String titulo = getIntent().getStringExtra("titulo");
        String categoria = getIntent().getStringExtra("categoria");
        String prioridade = getIntent().getStringExtra("prioridade");
        String status = getIntent().getStringExtra("status");
        String descricao = getIntent().getStringExtra("descricao");

        if (chamadoId == 0) {
            Toast.makeText(this, "Erro: chamado inválido.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        textViewTitulo.setText(titulo != null ? titulo : "");
        textViewCategoria.setText("Categoria: " + (categoria != null ? categoria : ""));
        textViewPrioridade.setText("Prioridade: " + (prioridade != null ? prioridade : ""));
        textViewStatus.setText("Status: " + (status != null ? status : ""));
        editTextDescricao.setText(descricao != null ? descricao : "");

        carregarMensagensChamado(chamadoId);

        // 👉 AÇÃO DO BOTÃO ENVIAR (corrigida)
        buttonEnviarMensagem.setOnClickListener(v -> {
            String texto = editTextNovaMensagem.getText().toString().trim();

            if (texto.isEmpty()) {
                Toast.makeText(this, "Digite uma mensagem.", Toast.LENGTH_SHORT).show();
                return;
            }

            buttonEnviarMensagem.setEnabled(false);

            mensagemController.enviarMensagem(chamadoId, texto, new Callback<MensagemChamado>() {
                @Override
                public void onResponse(Call<MensagemChamado> call, Response<MensagemChamado> response) {

                    buttonEnviarMensagem.setEnabled(true);

                    if (response.isSuccessful()) {

                        editTextNovaMensagem.setText("");

                        carregarMensagensChamado(chamadoId);

                        Toast.makeText(ActivityDetalhesChamados.this,
                                "Mensagem enviada com sucesso!",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ActivityDetalhesChamados.this,
                                "Erro ao enviar mensagem.",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MensagemChamado> call, Throwable t) {

                    buttonEnviarMensagem.setEnabled(true);

                    Toast.makeText(ActivityDetalhesChamados.this,
                            "Falha na comunicação.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        });

        buttonVoltar.setOnClickListener(v -> finish());

        buttonFinalizarChamado.setOnClickListener(v -> {
            chamadoController.finalizarChamado(chamadoId, new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ActivityDetalhesChamados.this,
                                "Chamado finalizado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ActivityDetalhesChamados.this,
                                "Erro ao finalizar chamado.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ActivityDetalhesChamados.this,
                            "Falha ao finalizar chamado.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarMensagensChamado(chamadoId);
    }

    private void carregarMensagensChamado(int idChamado) {
        mensagemController.listarMensagensPorChamado(idChamado, new Callback<List<MensagemChamado>>() {
            @Override
            public void onResponse(Call<List<MensagemChamado>> call, Response<List<MensagemChamado>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    listaMensagens.clear();
                    listaMensagens.addAll(response.body());
                    adapter.notifyDataSetChanged();

                    recyclerViewMensagens.scrollToPosition(listaMensagens.size() - 1);

                } else {
                    Toast.makeText(ActivityDetalhesChamados.this,
                            "Funcionalidade de mensagens será revisada na V02.",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MensagemChamado>> call, Throwable t) {
                Toast.makeText(ActivityDetalhesChamados.this,
                        "Falha na comunicação com a API.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
