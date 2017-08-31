package com.example.android.listadelivros;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Util {

    public static String consultaDadoInformado = null;

    public static String removeAcento(String str) {
        str = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }

    public static String encode(String valorDigitado) {
        try {
            return consultaDadoInformado = URLEncoder.encode(valorDigitado, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

