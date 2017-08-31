package com.example.android.listadelivros;


public class DadosLivro {

    private String mTitulo;
    private String mDescricao;
    private String mAutor;
    private String mImagemLivro;
    private String mExibirLivroLoja;

    public DadosLivro(String titulo, String descricao, String autor,
                      String imagem, String ExibirLivroLoja){
        mTitulo = titulo;
        mDescricao = descricao;
        mAutor = autor;
        mImagemLivro = imagem;
        mExibirLivroLoja = ExibirLivroLoja;
    }

    public String getTitulo(){
        return mTitulo;
    }

    public String getDescricao(){
        return mDescricao;
    }

    public String getAutor(){
        return mAutor;
    }

    public String getImagem(){
        return mImagemLivro;
    }

    public String getComprarLivro(){
        return mExibirLivroLoja;
    }


}
