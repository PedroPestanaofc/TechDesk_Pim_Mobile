package com.techdesk.techdeskmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.controller.LoginController;
import com.techdesk.techdeskmobile.model.Usuario;
import com.techdesk.techdeskmobile.util.Utils;

// View responsável pela interface de Login do usuário.
// Arquitetura utilizada: MVC — esta classe representa apenas a camada de visualização da interface.
// Autor: Pedro Pestana

public class ActivityTelaEntrarTD extends AppCompatActivity {


    private EditText etEmail, etSenha;
    private Button btnEntrar;
    private TextView txtMessage;


    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_entrar_td);


        etEmail = findViewById(R.id.textInputEditText);
        etSenha = findViewById(R.id.textInputEditText3);
        btnEntrar = findViewById(R.id.button8);
        txtMessage = findViewById(R.id.textViewMessage);

        // Aqui está ocorrendo o instanciamento do Controller
        loginController = new LoginController(this);

        // Listener do botão de Login
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Coletando os dados de login
                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();

                // Validação simples antes de enviar ao controller - para verificar se todos os campos estão preenchidos
                if (email.isEmpty() || senha.isEmpty()) {
                    mostrarMensagem("Preencha todos os campos.");
                    return;
                }


                realizarLogin(email, senha);
            }
        });
    }

    // Metodo responsável por acionar o controller e tratar o retorno da ação.

    private void realizarLogin(String email, String senha) {
        loginController.login(email, senha, new LoginController.LoginCallback() {
            @Override
            public void onSuccess(Usuario usuario) {
                Utils.showToast(ActivityTelaEntrarTD.this, "Login bem-sucedido!");

                startActivity(new Intent(ActivityTelaEntrarTD.this, ActivityTelaPrincipal.class));
                finish();
            }

            @Override
            public void onError(String mensagem) {
                mostrarMensagem(mensagem);
            }
        });
    }

    // Caso de erro, vai exibir uma mensagem na tela
    private void mostrarMensagem(String mensagem) {
        txtMessage.setText(mensagem);
        txtMessage.setVisibility(View.VISIBLE);
    }
}
