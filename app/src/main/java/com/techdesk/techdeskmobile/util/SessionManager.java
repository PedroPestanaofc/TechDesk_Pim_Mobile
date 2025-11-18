package com.techdesk.techdeskmobile.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.techdesk.techdeskmobile.model.Usuario;

// Classe responsável por gerenciar a sessão do usuário no aplicativo.
// Ela funciona como um mini banco de dados local.
// autor: Pedro Pestana
public class SessionManager {

    // Nome do arquivo de sessão local
    private static final String PREF_NAME = "TechDeskSession";

    // Chaves utilizadas para salvar os dados do usuário
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_EMAIL = "user_email";
    private static SessionManager instance;

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.preferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    // Metodo para garantir que apenas uma instância seja criada
    public static synchronized SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    // Salva informações básicas da sessão do usuário
    public void saveSession(int userId, String email) {
        Log.d("SessionManager", "Salvando sessão: ID=" + userId + ", Email=" + email);

        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
    }

    // Retorna o ID salvo, ou -1 caso não exista
    public int getUserId() {
        int id = preferences.getInt(KEY_USER_ID, -1);
        Log.d("SessionManager", "Recuperado userId=" + id);
        return id;
    }
    public String getUserEmail() {
        return preferences.getString(KEY_USER_EMAIL, null);
    }

    // Constrói um objeto Usuario baseado nos dados armazenados localmente
    public Usuario getUsuario() {
        int id = getUserId();
        String email = getUserEmail();

        if (id == -1 || email == null) {
            Log.w("SessionManager", "Nenhum usuário encontrado na sessão.");
            return null;
        }
        return new Usuario(id, null, email, null, null, null, null, null);
    }

    // Verifica se há uma sessão ativa
    public boolean isLoggedIn() {
        return getUserId() != -1 && getUserEmail() != null;
    }

    // Limpa todas as informações armazenadas
    public void clearSession() {
        Log.d("SessionManager", "Limpando sessão.");
        editor.clear();
        editor.apply();
    }
}
