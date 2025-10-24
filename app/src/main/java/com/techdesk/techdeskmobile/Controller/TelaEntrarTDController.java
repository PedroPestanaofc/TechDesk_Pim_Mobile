package com.techdesk.techdeskmobile.Controller;

//-------------------------------------------------------------------
// Classe: TelaEntrarController
// Descrição: Criada para controlar a lógica do login
// @author Pedro Pestana
// @company TechDesk
// @since 08/10/2025
// @version 1.0
//-------------------------------------------------------------------

import com.techdesk.techdeskmobile.Model.TelaEntrarTDModel;

public class TelaEntrarTDController {

    // Metodo que simula a lógica de autenticação
    public TelaEntrarTDModel login(String username, String password) {
        TelaEntrarTDModel model = new TelaEntrarTDModel();

        // Simulação de login (essa lógica pode ser substituída por autenticação real)
        if (username.equals("user") && password.equals("password")) {
            model.setLoginSuccessful(true);
        } else {
            model.setLoginSuccessful(false);
        }

        return model;
    }
}
