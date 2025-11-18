package com.techdesk.techdeskmobile.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.model.Chamado;

import java.util.List;

// Adapter responsável por inflar os layouts dos itens da lista de chamados (RecyclerView).
// Inclui lógica para formatação do status (cor) e tratamento do evento de clique.
// Autor: Pedro Pestana
public class ChamadoAdapter extends RecyclerView.Adapter<ChamadoAdapter.ChamadoViewHolder> {

    private final List<Chamado> listaChamados;
    private final Context context;

    public ChamadoAdapter(List<Chamado> listaChamados, Context context) {
        this.listaChamados = listaChamados;
        this.context = context;
    }

    @NonNull
    @Override
    public ChamadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_chamado, parent, false);
        return new ChamadoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChamadoViewHolder holder, int position) {
        Chamado chamado = listaChamados.get(position);


        holder.tvId.setText(chamado.getIdChamado() != null ? String.valueOf(chamado.getIdChamado()) : "");
        holder.tvTitulo.setText(chamado.getTitulo() != null ? chamado.getTitulo() : "");
        holder.tvCategoria.setText(chamado.getCategoriaNome() != null ? chamado.getCategoriaNome() : "");
        holder.tvPrioridade.setText(chamado.getNivel() != null ? chamado.getNivel() : "");
        holder.tvStatus.setText(chamado.getStatus() != null ? chamado.getStatus() : "");

        // Definição simples da cor do status
        if (chamado.getStatus() != null) {
            switch (chamado.getStatus().toLowerCase()) {
                case "aberto":
                    holder.tvStatus.setTextColor(Color.parseColor("#008000"));
                    break;
                case "em andamento":
                    holder.tvStatus.setTextColor(Color.parseColor("#FFA500"));
                    break;
                case "concluido":
                    holder.tvStatus.setTextColor(Color.parseColor("#2196F3"));
                    break;
                case "cancelado":
                    holder.tvStatus.setTextColor(Color.parseColor("#FF0000"));
                    break;
                default:
                    holder.tvStatus.setTextColor(Color.BLACK);
                    break;
            }
        }

        // Valores finais para enviar via Intent
        final int idChamadoFinal = chamado.getIdChamado() != null ? chamado.getIdChamado() : -1;

        holder.itemView.setOnClickListener(v -> {

            // Aqui ocorre o BLOQUEIO, Chamado fechado não abre detalhes
            if (chamado.getStatus() != null &&
                    chamado.getStatus().equalsIgnoreCase("Fechado")) {

                Toast.makeText(context,
                        "Este chamado está fechado e não pode ser acessado.",
                        Toast.LENGTH_SHORT).show();
                return; // <-- impede abrir a Activity
            }

            // Abre a Activity normalmente
            Intent intent = new Intent(context, ActivityDetalhesChamados.class);
            intent.putExtra("chamadoId", idChamadoFinal);
            intent.putExtra("titulo", chamado.getTitulo());
            intent.putExtra("status", chamado.getStatus());
            intent.putExtra("descricao", chamado.getDescricao());
            intent.putExtra("categoria", chamado.getCategoriaNome());
            intent.putExtra("prioridade", chamado.getNivel());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaChamados.size();
    }

    static class ChamadoViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvTitulo, tvCategoria, tvPrioridade, tvStatus;

        public ChamadoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvCategoria = itemView.findViewById(R.id.tvCategoria);
            tvPrioridade = itemView.findViewById(R.id.tvPrioridade);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
