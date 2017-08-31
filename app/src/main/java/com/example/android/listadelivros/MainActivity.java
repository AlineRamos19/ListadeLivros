package com.example.android.listadelivros;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected String dadosLivroDigitado;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView pesquisarLivro = (TextView) findViewById(R.id.pesquisar);
        pesquisarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText dadosLivro = (EditText) findViewById(R.id.dados_livro);
                dadosLivroDigitado = dadosLivro.getText().toString();

                if(dadosLivroDigitado == null || dadosLivroDigitado.trim().isEmpty()){
                    Toast.makeText(MainActivity.this , "Favor inserir alguma informação",
                            Toast.LENGTH_SHORT).show();
                } else {

                    String strDadosDigitados = Util.removeAcento(dadosLivroDigitado);
                    Util.encode(strDadosDigitados);

                    Intent intent = new Intent(MainActivity.this, ExibirListaLivros.class);
                    startActivity(intent);

                }
            }
        });
    }
}
