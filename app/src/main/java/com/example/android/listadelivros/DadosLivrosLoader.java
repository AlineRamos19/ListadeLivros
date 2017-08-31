package com.example.android.listadelivros;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;


public class DadosLivrosLoader extends AsyncTaskLoader<List<DadosLivro>> {

    private String mGoogleLivroURL = "https://www.googleapis.com/books/v1/volumes?q="
            + Util.consultaDadoInformado;

    public DadosLivrosLoader(Context contexto) {
        super(contexto);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<DadosLivro> loadInBackground() {
        if (mGoogleLivroURL == null) {
            return null;
        }

        List<DadosLivro> informacoesLivros = ConsultaRequisicao.buscarDadosLivro(mGoogleLivroURL);
        return informacoesLivros;
    }
}
