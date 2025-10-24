package com.techdesk.techdeskmobile.Controller;

import android.content.Intent;
import com.techdesk.techdeskmobile.Model.TermosPrivacidadeModel;
import com.techdesk.techdeskmobile.View.TermosPrivacidade;

public class TermosPrivacidadeController {

    private TermosPrivacidade view;
    private TermosPrivacidadeModel model;

    public TermosPrivacidadeController(TermosPrivacidade view) {
        this.view = view;
        this.model = new TermosPrivacidadeModel();
    }

    public void onVoltarClicked() {
        view.finish(); // Fechar a tela atual e voltar para a anterior
    }

    // Lógica para salvar a aceitação dos termos
    public void aceitarTermos() {
        model.aceitarTermos();
    }

    public void recusarTermos() {
        model.recusarTermos();
    }
}
