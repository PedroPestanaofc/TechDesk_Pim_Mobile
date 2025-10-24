package com.techdesk.techdeskmobile.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.techdesk.techdeskmobile.Model.CriarContaModel;
import com.techdesk.techdeskmobile.R;
import com.techdesk.techdeskmobile.View.TelaCriarContaTD;

public class CriarContaController extends AppCompatActivity {

    private TelaCriarContaTD telaCriarConta; // Referência para a View
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ativa o modo Edge-to-Edge
        setContentView(R.layout.activity_tela_criar_conta_td);

        // Configura o padding para status/navigation bars (evita sobreposição)
        View mainLayout = findViewById(R.id.main);
        if (mainLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Inicializa a View
        telaCriarConta = findViewById(R.id.telaCriarConta); // Certifique-se de que está no XML

        // Configura o clique no botão "Criar Conta"
        Button buttonCriarConta = findViewById(R.id.button8);
        buttonCriarConta.setOnClickListener(v -> criarConta());
    }

    // Metodo para criar conta
    private void criarConta() {
        // Pega os dados inseridos na tela (View)
        String nome = telaCriarConta.getNome();
        String email = telaCriarConta.getEmail();
        String senha = telaCriarConta.getSenha();

        // Cria um objeto do Model com os dados obtidos
        CriarContaModel criarContaModel = new CriarContaModel(nome, email, senha);

        // Tenta criar a conta
        if (criarContaModel.criarConta()) {
            showToast("Conta Criada com Sucesso!", true);
        } else {
            showToast("Erro ao criar conta! Verifique os dados inseridos.", false);
        }
    }

    // Meetodo para exibir o Toast por 2 segundos e redirecionar
    private void showToast(String message, boolean sucesso) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();

        // Após exibir o Toast, redireciona para a TelaEntrarTD em caso de sucesso
        if (sucesso) {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            navigateToTelaEntrar();  // Redireciona para a TelaEntrarTD
                        }
                    },
                    2000);  // Espera 2 segundos antes de redirecionar
        }
    }

    // Metodo para redirecionar para a Tela de Login ou Tela Principal
    private void navigateToTelaEntrar() {
        Intent intent = new Intent(CriarContaController.this, TelaEntrarTD.class);
        startActivity(intent);
        finish(); // Fecha a tela de criação de conta (impede de voltar com o botão "voltar")
    }
}
