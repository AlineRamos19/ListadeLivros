package com.example.android.listadelivros;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DadosLivrosAdapter extends ArrayAdapter<DadosLivro> {


    public DadosLivrosAdapter(Context contexto, List<DadosLivro> informacoesLivros) {
        super(contexto, 0, informacoesLivros);
    }

    @Override
    public View getView(int posicao, View converteVisualizacao, ViewGroup parente) {

        View visualizacao = converteVisualizacao;
        if (visualizacao == null) {
            visualizacao = LayoutInflater.from(getContext()).inflate(R.layout.dados_livros_lista,
                    parente, false);
        }

        DadosLivro listaLivros = getItem(posicao);

        TextView titulo = (TextView) visualizacao.findViewById(R.id.titulo);
        titulo.setText(listaLivros.getTitulo());

        TextView descricao = (TextView) visualizacao.findViewById(R.id.descricao);
        descricao.setText(Html.fromHtml((listaLivros.getDescricao())));

        TextView autor = (TextView) visualizacao.findViewById(R.id.autor);
        autor.setText(listaLivros.getAutor());

        ImageView imagemLivro = (ImageView) visualizacao.findViewById(R.id.imagem);
        String urlImagemLivro = listaLivros.getImagem();
        URL urlImagem;
        try {
            urlImagem = new URL(urlImagemLivro);
            new ImagemUrlTask(imagemLivro).execute(urlImagem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return visualizacao;
    }
}


