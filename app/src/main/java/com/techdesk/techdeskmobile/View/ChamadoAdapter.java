package com.techdesk.techdeskmobile.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techdesk.techdeskmobile.Model.Chamado;
import com.techdesk.techdeskmobile.R;

import java.util.List;

public class ChamadoAdapter extends BaseAdapter {

    private Context context;
    private List<Chamado> listaChamados;

    public ChamadoAdapter(Context context, List<Chamado> listaChamados) {
        this.context = context;
        this.listaChamados = listaChamados;
    }

    @Override
    public int getCount() {
        return listaChamados.size();
    }

    @Override
    public Object getItem(int position) {
        return listaChamados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(listaChamados.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chamado, parent, false);
        }

        Chamado chamado = listaChamados.get(position);

        TextView titulo = convertView.findViewById(R.id.tituloChamado);
        TextView responsavel = convertView.findViewById(R.id.responsavelChamado);
        TextView status = convertView.findViewById(R.id.statusChamado);

        titulo.setText(chamado.getTitulo());
        responsavel.setText(chamado.getResponsavel());
        status.setText(chamado.getStatus());

        return convertView;
    }
}
