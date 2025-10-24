package com.techdesk.techdeskmobile.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;
import com.techdesk.techdeskmobile.Controller.TermosPrivacidadeController;
import com.techdesk.techdeskmobile.R;

public class TermosPrivacidade extends AppCompatActivity {

    private TermosPrivacidadeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_privacidade);

        controller = new TermosPrivacidadeController(this);

        // Ajuste de padding seguro
        ScrollView mainLayout = findViewById(R.id.scrollView2); // id do ConstraintLayout no XML
        if (mainLayout != null) {
            mainLayout.setPadding(
                    getResources().getDimensionPixelSize(R.dimen.padding_left),
                    getResources().getDimensionPixelSize(R.dimen.padding_top),
                    getResources().getDimensionPixelSize(R.dimen.padding_right),
                    getResources().getDimensionPixelSize(R.dimen.padding_bottom)
            );
        }

        // Botão de voltar
        Button btnVoltar = findViewById(R.id.buttonvoltar);
        if (btnVoltar != null) {
            btnVoltar.setOnClickListener(v -> controller.onVoltarClicked());
        }
    }
}
