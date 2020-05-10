package com.example.multiaplicacion;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MonedaActivity extends AppCompatActivity {

    private TextView titulo;
    private Typeface daywalker;
    //variables
    private EditText Valor;
    private TextView TipoConversion;
    private Switch Moneda;
    private TextView Resultado;
    private Button Convertir;
    private double A;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneda);
        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_converunidades);


        String fuente = "fuentes/Daywalker.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);

        titulo = (TextView)findViewById(R.id.textView);
        titulo.setTypeface(daywalker);

        Moneda = (Switch)findViewById(R.id.swMoneda);
        TipoConversion = (TextView)findViewById(R.id.lblTipoConversion);
        Convertir = (Button)findViewById(R.id.btnConversion);
        Valor =(EditText)findViewById(R.id.txtValor);
        Resultado = (TextView)findViewById(R.id.lblResultado);

        Moneda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    TipoConversion.setText("PESOS A EUROS");
                }
                else{
                    TipoConversion.setText("EUROS A PESOS");
                }
            }
        });




        Convertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Moneda.isChecked()){
                    //Pesos A Euros
                    A=Double.parseDouble(Valor.getText().toString());
                    Toast.makeText(MonedaActivity.this, "Valor: "+A, Toast.LENGTH_SHORT).show();
                    A=A*0.00026;
                    Resultado.setText("Euro: "+A);

                }else{
                    //Pesos A Euros
                    A=Double.parseDouble(Valor.getText().toString());
                    Toast.makeText(MonedaActivity.this, "Valor: "+A, Toast.LENGTH_SHORT).show();
                    A=A*3778.72;
                    Resultado.setText("Peso Colombiano: "+A);
                }

            }
        });
    }
}
