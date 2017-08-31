package com.example.android.listadelivros;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExibirListaLivros extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<DadosLivro>> {

    private TextView mEstadoVazioTextView;
    private static final int DADOSLIVROS_ID_LOADER = 1;
    private DadosLivrosAdapter mAdapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listview = (ListView) findViewById(R.id.lista);
        mAdapter = new DadosLivrosAdapter(this, new ArrayList<DadosLivro>());
        listview.setAdapter(mAdapter);

        mEstadoVazioTextView = (TextView) findViewById(R.id.visualizacaoVazia);
        listview.setEmptyView(mEstadoVazioTextView);

        ConnectivityManager verificarConexaoInternet = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo informacaoRede = verificarConexaoInternet.getActiveNetworkInfo();

        if (informacaoRede != null && informacaoRede.isConnected()) {

            final LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(DADOSLIVROS_ID_LOADER, null, this);
        } else {
            View indicadorProcessamento = findViewById(R.id.indicadorProcessamento);
            indicadorProcessamento.setVisibility(View.GONE);
            mEstadoVazioTextView.setText(getString(R.string.sem_conexao_rede));
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View visualizacao,
                                    int posicao, long l) {

                DadosLivro livroClicado = mAdapter.getItem(posicao);

                Uri informacoesLivroUri = Uri.parse(livroClicado.getComprarLivro().trim());
                Intent siteGoogleLivros = new Intent(Intent.ACTION_VIEW, informacoesLivroUri);
                if (siteGoogleLivros.resolveActivity(getPackageManager()) != null) {
                    startActivity(siteGoogleLivros);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public android.content.Loader<List<DadosLivro>> onCreateLoader(int i, Bundle bundle) {

        return new DadosLivrosLoader(this);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<DadosLivro>> loader, List<DadosLivro>
            informacoesLivros) {

        View indicadorProcessamento = findViewById(R.id.indicadorProcessamento);
        indicadorProcessamento.setVisibility(View.GONE);

        mEstadoVazioTextView.setBackgroundResource(R.drawable.notificacao_sem_resulltado);
        mEstadoVazioTextView.setText(getString(R.string.sem_resultados_livro));


        mAdapter.clear();

        if (informacoesLivros != null && !informacoesLivros.isEmpty()) {
            mAdapter.addAll(informacoesLivros);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<DadosLivro>> loader) {
        mAdapter.clear();
    }

}
