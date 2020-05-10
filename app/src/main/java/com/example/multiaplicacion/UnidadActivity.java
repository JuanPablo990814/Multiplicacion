package com.example.multiaplicacion;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class UnidadActivity extends AppCompatActivity {

    private TextView titulo;
    private Typeface daywalker;

    //variables
    private EditText Valoru;
    private TextView TipoConversionu;
    private Switch Unidadu;
    private TextView Resultadou;
    private Button Convertiru;
    private double Au;

    private EditText Valoru2;
    private TextView TipoConversionu2;
    private Switch Unidadu2;
    private TextView Resultadou2;
    private Button Convertiru2;
    private double Au2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidad);

        //ActionBar Mostrar Icono al lado del nombre
        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_converunidades);

        String fuente = "fuentes/Daywalker.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);

        titulo = (TextView)findViewById(R.id.textView);
        titulo.setTypeface(daywalker);

        Valoru =(EditText)findViewById(R.id.txtValoru);
        TipoConversionu = (TextView)findViewById(R.id.lblTipoConversionu);
        Unidadu = (Switch)findViewById(R.id.swUnidadu);
        Resultadou = (TextView)findViewById(R.id.lblResultadou);
        Convertiru = (Button)findViewById(R.id.btnConversionu);

        Valoru2 =(EditText)findViewById(R.id.txtValoru2);
        TipoConversionu2 = (TextView)findViewById(R.id.lblTipoConversionu2);
        Unidadu2 = (Switch)findViewById(R.id.swUnidadu2);
        Resultadou2 = (TextView)findViewById(R.id.lblResultadou2);
        Convertiru2 = (Button)findViewById(R.id.btnConversionu2);

        Unidadu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    TipoConversionu.setText("MILLAS A KILOMETROS");
                }
                else{
                    TipoConversionu.setText("KILOMETROS A MILLAS");
                }
            }
        });




        Convertiru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Unidadu.isChecked()){
                    //Millas A Kilometros
                    Au=Double.parseDouble(Valoru.getText().toString());
                    Au=Au*1.60934;
                    Resultadou.setText("Kilometros: "+Au);

                }else{
                    //Kilometros a millas
                    Au=Double.parseDouble(Valoru.getText().toString());
                    Au=Au*0.621371;
                    Resultadou.setText("Millas: "+Au);
                }

            }
        });

        Unidadu2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    TipoConversionu2.setText("GALONES A LITROS");
                }
                else{
                    TipoConversionu2.setText("LITROS A GALONES");
                }
            }
        });

        Convertiru2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Unidadu2.isChecked()){
                    //Galones a litros
                    Au2=Double.parseDouble(Valoru2.getText().toString());
                    Au2=Au2*3.78541;
                    Resultadou2.setText("Litros: "+Au2);

                }else{
                    //litros a galones
                    Au2=Double.parseDouble(Valoru2.getText().toString());
                    Au2=Au2*0.264172;
                    Resultadou2.setText("Galones: "+Au2);
                }

            }
        });

    }
}
