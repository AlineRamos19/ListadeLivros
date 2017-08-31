package com.example.android.listadelivros;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProcessarLivros {

    private static final String LOG_TAG = ProcessarLivros.class.getSimpleName();

    public static List<DadosLivro> extrairDadosJson(String dadosLivrosJson) {

        if (TextUtils.isEmpty(dadosLivrosJson)) {
            return null;
        }

        List<DadosLivro> informacoesLivro = new ArrayList<>();

        try {
            JSONObject respostaJason = new JSONObject(dadosLivrosJson);
            JSONArray dadosLivroArray = respostaJason.optJSONArray("items");

            for (int i = 0; i < dadosLivroArray.length(); i++) {

                JSONObject livroAtual = dadosLivroArray.optJSONObject(i);
                JSONObject informacaoVolume = livroAtual.optJSONObject("volumeInfo");

                String tituloLivro = " ";
                if (informacaoVolume.has("title")) {
                    tituloLivro = informacaoVolume.optString("title");
                }

                JSONObject imagemUrl;
                String urlImagemLivro = " ";
                if (informacaoVolume.has("imageLinks")) {
                    imagemUrl = informacaoVolume.optJSONObject("imageLinks");
                    if (imagemUrl.has("thumbnail")) {
                        urlImagemLivro = imagemUrl.optString("thumbnail");
                    }
                }

                String resenhaLivro = " ";
                JSONObject resumoLivro;
                if (livroAtual.has("searchInfo")) {
                    resumoLivro = livroAtual.optJSONObject("searchInfo");
                    if (resumoLivro.has("textSnippet")) {
                        resenhaLivro = resumoLivro.optString("textSnippet");
                    }
                }

                JSONArray listaAutor = informacaoVolume.optJSONArray("authors");
                String autor = null;

                if (listaAutor != null && listaAutor.length() > 0) {
                    for (int j = 0; j < listaAutor.length(); j++) {
                        autor = listaAutor.optString(j);
                    }
                }

                String ExibirLivroLoja = " ";
                if (informacaoVolume.has("infoLink")) {
                    ExibirLivroLoja = informacaoVolume.optString("infoLink");
                }

                DadosLivro inforLivro = new DadosLivro(tituloLivro, resenhaLivro, autor,
                        urlImagemLivro, ExibirLivroLoja);
                informacoesLivro.add(inforLivro);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problema ao analisar os resultados JSON", e);
        }
        return informacoesLivro;
    }
}
