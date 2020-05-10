package com.example.multiaplicacion;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class TraductorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView titulo;
    private Typeface daywalker;

    private TextView Traduccion;
    private EditText Palabra;
    private Switch Tipo;
    private Button Traducir;
    private TextView Idioma;

    private String[][] miPalabra={};
    private Spinner SpiEspañol,SpiIngles;

    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traductor);

        miPalabra = new String[11][11];
        miPalabra[1][1]="Carro";
        miPalabra[1][2]="Car";
        miPalabra[2][1]="Avion";
        miPalabra[2][2]="Plane";
        miPalabra[3][1]="Tren";
        miPalabra[3][2]="Train";
        miPalabra[4][1]="Casa";
        miPalabra[4][2]="House";
        miPalabra[5][1]="Imagen";
        miPalabra[5][2]="Picture";
        miPalabra[6][1]="Limpio";
        miPalabra[6][2]="Clean";
        miPalabra[7][1]="Humo";
        miPalabra[7][2]="Smoke";
        miPalabra[8][1]="Espada";
        miPalabra[8][2]="Sword";
        miPalabra[9][1]="Sol";
        miPalabra[9][2]="Sun";
        miPalabra[10][1]="Desayuno";
        miPalabra[10][2]="Breakfast";

        //ActionBar Mostrar Icono al lado del nombre
        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_traductorr);

        String fuente = "fuentes/Daywalker.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);

        titulo = (TextView)findViewById(R.id.textView);
        titulo.setTypeface(daywalker);

        Traduccion = (TextView)findViewById(R.id.lblResultado);
        Palabra = (EditText)findViewById(R.id.txtPalabra);
        Tipo = (Switch)findViewById(R.id.swTraduccion);
        Traducir = (Button)findViewById(R.id.btnTraducir);
        Idioma = (TextView)findViewById(R.id.lblqueaque);
        SpiEspañol= (Spinner)findViewById(R.id.spEspañol);
        SpiIngles = (Spinner)findViewById(R.id.spIngles);

        //mtLlenarPalabras();
        Tipo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Idioma.setText("ENGLISH TO SPANISH");
                }
                else{
                    Idioma.setText("ESPAÑOL A INGLES");
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(TraductorActivity.this,R.array.Español,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpiEspañol.setAdapter(adapter);
        SpiEspañol.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(TraductorActivity.this,R.array.Ingles,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpiIngles.setAdapter(adapter2);
        SpiIngles.setOnItemSelectedListener(this);

        Traducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PalaTraduc = Palabra.getText().toString();
                /*int codigo = 0;
                for(int i=0;i>=11;i++){
                    Toast.makeText(TraductorActivity.this, "Hago algo", Toast.LENGTH_SHORT).show();
                    if(PalaTraduc==miPalabra[codigo][2]){
                        Palabra.setText("Traduccion: "+miPalabra[codigo][1]);
                        codigo=0;
                    }
                    codigo++;
                }*/

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String Texto = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, ""+Texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
