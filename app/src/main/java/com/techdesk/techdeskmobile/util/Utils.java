package com.techdesk.techdeskmobile.util;

import android.content.Context;
import android.widget.Toast;

import java.security.MessageDigest;

// Classe utilitária destinada a reunir funções genéricas que podem ser utilizadas
// em qualquer parte do sistema.
// autor: Pedro Pestana

public class Utils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // Função para gerar hash usando o algoritmo MD5.
    public static String md5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] array = md.digest(s.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : array) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();

        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
}
