package com.techdesk.techdeskmobile.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.techdesk.techdeskmobile.Model.Chamado;
import com.techdesk.techdeskmobile.View.ChamadoAdapter;
import com.techdesk.techdeskmobile.View.DetalhesChamadoActivity;
import com.techdesk.techdeskmobile.R;

import java.util.ArrayList;
import java.util.List;

public class MeusChamadosController extends AppCompatActivity {

    private ListView listViewChamados;
    private Spinner spinnerStatus;
    private TextView tituloTela;

    private List<Chamado> listaChamados;
    private ChamadoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meus_chamados);

        // Ajuste seguro de padding para diferentes dispositivos
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa os componentes
        tituloTela = findViewById(R.id.textView3);
        spinnerStatus = findViewById(R.id.spinner);
        listViewChamados = findViewById(R.id.listView);

        // Cria e inicializa a lista de chamados
        listaChamados = new ArrayList<>();
        listaChamados.add(new Chamado("1", "Erro no login", "Suporte", "Alta", "Aberto", "Responsável 1"));
        listaChamados.add(new Chamado("2", "Atualização App", "TI", "Média", "Fechado", "Responsável 2"));
        listaChamados.add(new Chamado("3", "Erro no pagamento", "Financeiro", "Alta", "Em andamento", "Responsável 3"));
        listaChamados.add(new Chamado("4", "Nova funcionalidade", "Desenvolvimento", "Baixa", "Aberto", "Responsável 4"));

        // Cria e seta o adapter
        adapter = new ChamadoAdapter(this, listaChamados);
        listViewChamados.setAdapter(adapter);

        // Listener do Spinner para filtrar por status
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String statusSelecionado = parent.getItemAtPosition(position).toString();
                filtrarPorStatus(statusSelecionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filtrarPorStatus("Todos");
            }
        });

        // Listener do ListView para abrir detalhes do chamado
        listViewChamados.setOnItemClickListener((parent, view, position, id) -> {
            Chamado chamadoSelecionado = listaChamados.get(position);

            // Cria uma nova Intent para abrir a tela de detalhes
            Intent intent = new Intent(MeusChamadosController.this, DetalhesChamadoActivity.class);
            intent.putExtra("ID", chamadoSelecionado.getId());
            intent.putExtra("TITULO", chamadoSelecionado.getTitulo());
            intent.putExtra("RESPONSAVEL", chamadoSelecionado.getResponsavel());
            intent.putExtra("PRIORIDADE", chamadoSelecionado.getPrioridade());
            intent.putExtra("STATUS", chamadoSelecionado.getStatus());

            // Inicia a nova Activity
            startActivity(intent);
        });
    }

    // Metodo para filtrar a lista de chamados
    private void filtrarPorStatus(String status) {
        List<Chamado> filtrados = new ArrayList<>();
        for (Chamado c : listaChamados) {
            if (status.equals("Todos") || c.getStatus().equals(status)) {
                filtrados.add(c);
            }
        }
        adapter = new ChamadoAdapter(this, filtrados);
        listViewChamados.setAdapter(adapter);
    }
}
