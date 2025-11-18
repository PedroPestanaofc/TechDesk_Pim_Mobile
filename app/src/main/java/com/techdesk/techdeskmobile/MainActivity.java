package com.techdesk.techdeskmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.techdesk.techdeskmobile.view.ActivityTelaUsuario;

// Classe principal do aplicativo, responsável por exibir a tela inicial (Splash Screen)
// e redirecionar o usuário para a tela de Login.
// autor: Pedro Pestana

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        // Handler responsável por simular o tempo de exibição da Splash (3 segundos)
        new Handler().postDelayed(() -> {

            // Após a Splash, o usuário é redirecionado para a tela inicial de login
            Intent intent = new Intent(MainActivity.this, ActivityTelaUsuario.class);
            startActivity(intent);

            finish();

        }, 3000); // 3000ms = 3 segundos
    }
}
