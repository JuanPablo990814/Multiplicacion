package com.example.multiaplicacion;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView titulo,autor,textodeco;
    private Typeface daywalker,silence;
    private ImageButton calendario,moneda,unidad,
            traductor,contactos,calculadora,reloj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionBar Mostrar Icono al lado del nombre
        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_launcher);

        //Fuentes
        String fuente = "fuentes/Daywalker.ttf";
        String fuente2 = "fuentes/Silence_Rocken.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);
        this.silence = Typeface.createFromAsset(getAssets(),fuente2);
        titulo = (TextView)findViewById(R.id.textView);
        titulo.setTypeface(daywalker);
        autor = (TextView)findViewById(R.id.Autor);
        textodeco = (TextView)findViewById(R.id.textoFinal);
        autor.setTypeface(silence);
        textodeco.setTypeface(silence);

        calendario = (ImageButton) findViewById(R.id.btnCalendario);
        moneda = (ImageButton) findViewById(R.id.btnMoneda);
        unidad = (ImageButton) findViewById(R.id.btnUnidad);
        traductor = (ImageButton) findViewById(R.id.btnTraductor);
        contactos = (ImageButton) findViewById(R.id.btnContactos);
        calculadora = (ImageButton) findViewById(R.id.btnCalculadora);
        reloj = (ImageButton) findViewById(R.id.btnReloj);

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ntAbrirCalendario();
                Prueba();
            }
        });
        moneda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntAbrirMoneda();
            }
        });

        unidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntAbrirUnidad();
            }
        });

        traductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntAbrirTraductor();
            }
        });
        contactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntAbrirContactos();
            }
        });

        reloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntAbrirReloj();
            }
        });

        calculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntAbrirCalculadora();
            }
        });


    }

    private void ntAbrirCalendario() {
        Intent intent = new Intent(MainActivity.this,CalendarioActivity.class);
        startActivity(intent);
    }

    private void ntAbrirMoneda() {
        Intent intent = new Intent(MainActivity.this,MonedaActivity.class);
        startActivity(intent);
    }

    private void ntAbrirUnidad() {
        Intent intent = new Intent(MainActivity.this,UnidadActivity.class);
        startActivity(intent);
    }

    private void ntAbrirTraductor() {
        Intent intent = new Intent(MainActivity.this,TraductorActivity.class);
        startActivity(intent);
    }

    private void ntAbrirContactos() {
        Intent intent = new Intent(MainActivity.this,AgendaActivity.class);
        startActivity(intent);
    }

    private void ntAbrirCalculadora() {
        Intent intent = new Intent(MainActivity.this,CalculadoraMain.class);
        startActivity(intent);
    }

    private void ntAbrirReloj() {
        Intent intent = new Intent(MainActivity.this,RelojMain.class);
        startActivity(intent);
    }

    private void Prueba(){
        Intent intent = new Intent(MainActivity.this,CalendarioActivity.class);
        startActivity(intent);
    }
}
