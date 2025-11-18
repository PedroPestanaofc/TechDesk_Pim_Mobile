// View criada para exibir a interface de Cadastro de Usuário (Tela de Criar Conta)
// Autor: Pedro Pestana


package com.techdesk.techdeskmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.controller.UsuarioController;
import com.techdesk.techdeskmobile.model.Usuario;
import com.techdesk.techdeskmobile.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityTelaCriarContaTD extends AppCompatActivity {


    private EditText etEmail, etSenha, etNome;

    private Button btnCriarConta;

    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_conta_td);

        etEmail = findViewById(R.id.textInputEditText);
        etSenha = findViewById(R.id.textInputEditText3);
        etNome = findViewById(R.id.textInputEditText4);
        btnCriarConta = findViewById(R.id.button8);
        txtMessage = findViewById(R.id.textViewMessage);

        // Evento de clique para iniciar o fluxo de criação de conta do usuario
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();
                String nome = etNome.getText().toString().trim();

                // Validação simples, para verificar se todos os campos foram preenchidos
                if (email.isEmpty() || senha.isEmpty() || nome.isEmpty()) {
                    txtMessage.setText("Preencha todos os campos.");
                    txtMessage.setVisibility(View.VISIBLE);
                } else {

                    // Instância do model Usuario (camada MODEL do MVC)
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setNome(nome);

                    // A senha deve ser enviada em hash MD5, conforme definida pela API
                    usuario.setSenhaHash(Utils.md5(senha));

                    usuario.setPerfil("Usuario");
                    usuario.setAtivo(true);

                    criarConta(usuario);
                }
            }
        });
    }

    //Metodo responsável por chamar o Controller, que faz a comunicação com a API.

    private void criarConta(Usuario usuario) {

        UsuarioController usuarioController = new UsuarioController();

        usuarioController.criarConta(usuario, new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                // Se a API retornar com sucesso, limpamos a tela e redirecionamos para a próxima tela
                if (response.isSuccessful() && response.body() != null) {

                    Utils.showToast(ActivityTelaCriarContaTD.this, "Conta criada com sucesso!");

                    // Limpeza dos campos após o cadastro
                    etEmail.setText("");
                    etSenha.setText("");
                    etNome.setText("");
                    txtMessage.setVisibility(View.INVISIBLE);

                    // Redireciona para a tela de Login após criar conta
                    startActivity(new Intent(ActivityTelaCriarContaTD.this, ActivityTelaEntrarTD.class));
                    finish();

                } else {
                    // Mensagem de erro caso dê erro
                    txtMessage.setText("Erro ao criar conta: " + response.message());
                    txtMessage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                // Falha na requisição com a API, pode acontecer quando tem problemas com o servidor ou rede.
                t.printStackTrace();
                txtMessage.setText("Erro ao criar conta: " + t.getMessage());
                txtMessage.setVisibility(View.VISIBLE);
            }
        });
    }
}
