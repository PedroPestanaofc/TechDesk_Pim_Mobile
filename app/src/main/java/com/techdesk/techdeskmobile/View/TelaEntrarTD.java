package com.techdesk.techdeskmobile.View;

//-------------------------------------------------------------------
// Classe: TelaEntrarTD
// Descrição: Aqui irá ocorrer a View, onde irá apresentar para o usuário
// usuário que está acessando suas funcionalidades.
// @author Pedro Pestana
// @company TechDesk
// @since 08/10/2025
// @version 1.0
//-------------------------------------------------------------------

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.Controller.TelaEntrarTDController;
import com.techdesk.techdeskmobile.Model.TelaEntrarTDModel;
import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.TelaPrincipal;

public class TelaEntrarTD extends AppCompatActivity {

    private TelaEntrarTDController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_entrar_td);

        controller = new TelaEntrarTDController();  // Inicializa o Controller

        EditText usernameEditText = findViewById(R.id.textInputEditText);
        EditText passwordEditText = findViewById(R.id.textInputEditText3);

        // Botão "Entrar"
        Button buttonEntrar = findViewById(R.id.button8);
        buttonEntrar.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Chama o Controller para realizar o login
            TelaEntrarTDModel model = controller.login(username, password);

            if (model.isLoginSuccessful()) {
                IrParaProximaTela();  // Navega para a próxima tela
            } else {
                // Exibe uma mensagem de erro
                Toast.makeText(TelaEntrarTD.this, "Login falhou! Verifique suas credenciais.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Navega para a próxima tela após login bem-sucedido
    private void IrParaProximaTela() {
        Intent intent = new Intent(TelaEntrarTD.this, TelaPrincipal.class);
        startActivity(intent);
        finish();  // Fecha a tela de login (impede de voltar com o botão "voltar")
    }
}
