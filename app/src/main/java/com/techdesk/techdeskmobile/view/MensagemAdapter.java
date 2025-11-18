package com.techdesk.techdeskmobile.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.model.MensagemChamado;
import com.techdesk.techdeskmobile.util.SessionManager;

import java.util.List;

// Adapter responsável por exibir as mensagens de um chamado (chat), utilizando RecyclerView.
// Implementa lógica para diferenciar a origem da mensagem (Usuário, Técnico ou TechBot)
// e alinhar o balão da mensagem (direita para o usuário, esquerda para outros).
// Autor: Pedro Pestana
public class MensagemAdapter extends RecyclerView.Adapter<MensagemAdapter.ViewHolder> {

    private final Context context;
    private final List<MensagemChamado> listaMensagens;
    private final SessionManager session;

    public MensagemAdapter(Context context, List<MensagemChamado> listaMensagens) {
        this.context = context;
        this.listaMensagens = listaMensagens;
        this.session = new SessionManager(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mensagem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MensagemChamado msg = listaMensagens.get(position);

        String autorFinal;

        if (msg.getIdUsuario() == 61) {
            autorFinal = "TechBot";
        } else if (msg.getIdUsuario() == session.getUserId()) {
            autorFinal = "Usuário";
        } else {
            autorFinal = "Técnico";
        }

        holder.txtAutor.setText(autorFinal);
        holder.txtMensagem.setText(msg.getMensagem());
        holder.txtData.setText(msg.getDataHora());

        LinearLayout.LayoutParams params =
                (LinearLayout.LayoutParams) holder.containerMensagem.getLayoutParams();

        if (msg.getIdUsuario() == session.getUserId()) {
            // Usuário → direita
            params.gravity = android.view.Gravity.END;
        } else {
            // Técnico / Bot → esquerda
            params.gravity = android.view.Gravity.START;
        }

        holder.containerMensagem.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return listaMensagens != null ? listaMensagens.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAutor, txtMensagem, txtData;
        LinearLayout containerMensagem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            containerMensagem = (LinearLayout) ((ViewGroup) itemView).getChildAt(0);

            txtAutor = itemView.findViewById(R.id.txtAutor);
            txtMensagem = itemView.findViewById(R.id.txtMensagem);
            txtData = itemView.findViewById(R.id.txtData);
        }
    }
}
