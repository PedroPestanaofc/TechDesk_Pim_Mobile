package com.techdesk.techdeskmobile.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techdesk.techdeskmobile.R;

public class TelaLoginCad extends LinearLayout {

    private Button buttonEntrar;
    private Button buttonCriarConta;
    private TextView textTermos;

    public TelaLoginCad(Context context) {
        super(context);
        init(context);
    }

    public TelaLoginCad(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TelaLoginCad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // Infla o layout da view
        inflate(context, R.layout.activity_segunda_tela, this);

        // Inicializa os componentes
        buttonEntrar = findViewById(R.id.button2);
        buttonCriarConta = findViewById(R.id.button3);
        textTermos = findViewById(R.id.textView2);
    }

    // Métodos para configurar os listeners
    public void setButtonEntrarClickListener(OnClickListener listener) {
        buttonEntrar.setOnClickListener(listener);
    }

    public void setButtonCriarContaClickListener(OnClickListener listener) {
        buttonCriarConta.setOnClickListener(listener);
    }

    public void setTextTermosClickListener(OnClickListener listener) {
        textTermos.setOnClickListener(listener);
    }
}
