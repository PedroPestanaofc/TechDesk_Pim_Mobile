package com.techdesk.techdeskmobile.Controller;

import android.content.Intent;

import com.techdesk.techdeskmobile.Model.TelaPrincipalModel;
import com.techdesk.techdeskmobile.View.TelaPrincipal;

public class TelaPrincipalController {

    private TelaPrincipal view;
    private TelaPrincipalModel model;

    public TelaPrincipalController(TelaPrincipal view) {
        this.view = view;
        this.model = new TelaPrincipalModel();
    }

    public void onAbrirChamadoClicked() {
        model.abrirChamados();
        Intent intent = new Intent(view, TelaAbrirChamados.class);
        view.startActivity(intent);
    }

    public void onHistoricoClicked() {
        model.visualizarHistorico();
        Intent intent = new Intent(view, MeusChamados.class);
        view.startActivity(intent);
    }

    public void onFalarComIAClicked() {
        model.falarComIA();
        Intent intent = new Intent(view, TelaIA.class);
        view.startActivity(intent);
    }

    public void onSairClicked() {
        model.sair();
        Intent intent = new Intent(view, MainActivity.class);
        view.startActivity(intent);
        view.finish();
    }
}
