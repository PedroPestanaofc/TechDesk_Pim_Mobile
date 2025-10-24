package com.techdesk.techdeskmobile.Model;

//-------------------------------------------------------------------
// Classe: TelaEntrarModel
// Descrição: Model criada para armazenar o estado do login
// @author Pedro Pestana
// @company TechDesk
// @since 08/10/2025
// @version 1.0
//-------------------------------------------------------------------

public class TelaEntrarTDModel {
    private boolean loginSuccessful;

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }
}
