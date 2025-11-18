package com.techdesk.techdeskmobile.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.model.Chamado;
import com.techdesk.techdeskmobile.api.ApiClient;
import com.techdesk.techdeskmobile.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// View responsável por exibir ao usuário a tela "Meus Chamados".
// Autor: Pedro Pestana

public class ActivityMeusChamados extends AppCompatActivity {

    private RecyclerView recyclerChamados;
    private ChamadoAdapter adapter;
    private List<Chamado> listaCompleta = new ArrayList<>();
    private Spinner spinnerStatus;
    private ApiService apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_chamados);

        // Inicialização dos componentes da interface
        recyclerChamados = findViewById(R.id.recyclerChamados);
        recyclerChamados.setLayoutManager(new LinearLayoutManager(this));

        spinnerStatus = findViewById(R.id.spinner);

        adapter = new ChamadoAdapter(new ArrayList<>(), this);
        recyclerChamados.setAdapter(adapter);

        // Chama o serviço da API
        apiService = ApiClient.getApiService();

        carregarChamados();

        // Listener do spinner que filtra os chamados conforme o status escolhido
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String statusSelecionado = parent.getItemAtPosition(position).toString();
                filtrarPorStatus(statusSelecionado);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // Metodo responsável por buscar os chamados na API.
    // Aqui também aplicamos o conceito de Controller, onde a View apenas cham a ação e aguarda o retorno.
    private void carregarChamados() {
        Call<List<Chamado>> call = apiService.listarChamados();

        // Chamada assíncrona para não travar
        call.enqueue(new Callback<List<Chamado>>() {
            @Override
            public void onResponse(Call<List<Chamado>> call, Response<List<Chamado>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaCompleta.clear();
                    listaCompleta.addAll(response.body());

                    // Assim que carregar, já aplica o filtro selecionado no spinner
                    filtrarPorStatus(spinnerStatus.getSelectedItem().toString());
                } else {
                    Toast.makeText(ActivityMeusChamados.this,
                            "Falha ao carregar chamados",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Chamado>> call, Throwable t) {
                Toast.makeText(ActivityMeusChamados.this,
                        "Erro: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    //Filtra os chamados conforme o status escolhido pelo usuário.
    // Aqui tive que tratar pequenas diferenças entre nomes vindos da API.
    private void filtrarPorStatus(String status) {
        List<Chamado> filtrados = new ArrayList<>();

        for (Chamado c : listaCompleta) {

            // Se o usuário escolheu "Todos", adicione todos os chamados à lista filtrada
            if (status.equalsIgnoreCase("Todos")) {
                filtrados.add(c);
            } else {
                String statusReal = c.getStatus();

                // Tratamento para pequenas diferenças de nomenclatura dos status
                if ("Fechado".equalsIgnoreCase(statusReal)) {
                    statusReal = "Fechado";
                } else if ("EmAndamento".equalsIgnoreCase(statusReal) ||
                        "Em andamento".equalsIgnoreCase(statusReal)) {
                    statusReal = "Em Andamento";
                }

                // Aplica o filtro apenas para o status escolhido
                if (statusReal.equalsIgnoreCase(status)) {
                    filtrados.add(c);
                }
            }
        }

        // Atualiza o adapter com a lista filtrada
        adapter = new ChamadoAdapter(filtrados, this);
        recyclerChamados.setAdapter(adapter);
    }
}
