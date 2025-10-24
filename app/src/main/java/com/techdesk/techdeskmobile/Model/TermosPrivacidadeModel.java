package com.techdesk.techdeskmobile.Model;

public class TermosPrivacidadeModel {
    // Ações relacionadas ao modelo de dados de privacidade
    public boolean isTermosAceitos() {
        // Retorna se os termos foram aceitos pelo usuário (simulação de um banco de dados ou preferência)
        // Implementar lógica para verificar se os termos foram aceitos
        return false; // Exemplo
    }

    public void aceitarTermos() {
        // Implementa a lógica de salvar a aceitação dos termos
        // Pode gravar em SharedPreferences ou banco de dados
    }

    public void recusarTermos() {
        // Implementa a lógica de recusar os termos, se necessário
    }
}
