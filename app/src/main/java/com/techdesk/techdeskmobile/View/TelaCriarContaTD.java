package com.techdesk.techdeskmobile.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.techdesk.techdeskmobile.R;

public class TelaCriarContaTD extends LinearLayout {

    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText senhaEditText;
    private Button criarContaButton;

    public TelaCriarContaTD(Context context) {
        super(context);
        init(context);
    }

    public TelaCriarContaTD(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TelaCriarContaTD(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // Infla o layout da view
        inflate(context, R.layout.activity_tela_criar_conta_td, this);

        // Inicializa os componentes
        nomeEditText = findViewById(R.id.nomeEditText);
        emailEditText = findViewById(R.id.emailEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        criarContaButton = findViewById(R.id.button8);
    }

    // Métodos para pegar os dados inseridos na View
    public String getNome() {
        return nomeEditText.getText().toString();
    }

    public String getEmail() {
        return emailEditText.getText().toString();
    }

    public String getSenha() {
        return senhaEditText.getText().toString();
    }

    // Configura o clique do botão
    public void setCriarContaClickListener(OnClickListener listener) {
        criarContaButton.setOnClickListener(listener);
    }
}
