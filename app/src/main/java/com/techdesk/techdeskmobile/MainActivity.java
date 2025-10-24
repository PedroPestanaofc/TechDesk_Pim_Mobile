package com.techdesk.techdeskmobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_GALERIA = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Exibe a tela inicial (logo/splash)
        setContentView(R.layout.activity_main);

        // Define a cor da status bar
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        // Aguarda 3 segundos e vai para a SegundaTela
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, TelaLoginCad.class);
            startActivity(intent);
            finish(); // Finaliza a splash
        }, 3000);
    }

    /**
     * Metodo opcional para abrir a galeria
     */
    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALERIA && resultCode == Activity.RESULT_OK && data != null) {
            Uri imagemSelecionada = data.getData();
            Toast.makeText(this, "Imagem selecionada: " + imagemSelecionada, Toast.LENGTH_SHORT).show();
        }
    }
}
