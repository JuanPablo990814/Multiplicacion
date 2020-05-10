package com.example.multiaplicacion;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalculadoraMain extends AppCompatActivity {

    private TextView titulo;
    private Typeface daywalker;

    public EditText pantalla,Opcional;
    public double Operan1,Operan2,Resultado;
    public String Operacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_main);

        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_calculadoraa);

        String fuente = "fuentes/Daywalker.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);

        titulo = (TextView)findViewById(R.id.textView);
        titulo.setTypeface(daywalker);

        pantalla=(EditText)findViewById(R.id.etPrincipal);
        Opcional=(EditText)findViewById(R.id.pantallaOpci);

        pantalla.setInputType(InputType.TYPE_NULL);
        Opcional.setInputType(InputType.TYPE_NULL);


    }

    public void btn1 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "1";
        pantalla.setText(cap);
    }

    public void btn2 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "2";
        pantalla.setText(cap);
    }

    public void btn3 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "3";
        pantalla.setText(cap);
    }

    public void btn4 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "4";
        pantalla.setText(cap);
    }

    public void btn5 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "5";
        pantalla.setText(cap);
    }

    public void btn6 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "6";
        pantalla.setText(cap);
    }

    public void btn7 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "7";
        pantalla.setText(cap);
    }

    public void btn8 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "8";
        pantalla.setText(cap);
    }

    public void btn9 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "9";
        pantalla.setText(cap);
    }

    public void btn0 (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + "0";
        pantalla.setText(cap);
    }

    public void btnPunto (View view){
        String cap = "";
        cap = pantalla.getText().toString();
        cap = cap + ".";
        pantalla.setText(cap);
    }



    public void Suma (View view){
        try{
            String cap = pantalla.getText().toString();
            Operan1 = Double.parseDouble(cap);
        }
        catch (NumberFormatException nfe){}
        pantalla.setText("");
        Operacion = "suma";
        Opcional.setText(Operan1+"+");
    }

    public void Resta (View view){
        try{
            String cap = pantalla.getText().toString();
            Operan1 = Double.parseDouble(cap);
        }
        catch (NumberFormatException nfe){}
        pantalla.setText("");
        Operacion = "resta";
        Opcional.setText(Operan1+"-");
    }

    public void Division (View view){
        try{
            String cap = pantalla.getText().toString();
            Operan1 = Double.parseDouble(cap);
        }
        catch (NumberFormatException nfe){}
        pantalla.setText("");
        Operacion = "division";
        Opcional.setText(Operan1+"/");
    }

    public void Raiz (View view){
        try{
            String cap = pantalla.getText().toString();
            Operan1 = Double.parseDouble(cap);
        }
        catch (NumberFormatException nfe){}
        pantalla.setText("v("+Operan1+")");
        Operacion = "raiz";
    }

    public void alCuadrado (View view){
        try{String aux = pantalla.getText().toString();
            Operan1 = Double.parseDouble(aux);}
        catch (NumberFormatException nfe){}
        pantalla.setText("");
        Resultado= Math.pow(Operan1,2);
        pantalla.setText(""+ Resultado);
        Operan1= Resultado;
        Opcional.setText("^2");
    }

    public void alCubo (View view){
        try{String aux = pantalla.getText().toString();
            Operan1 = Double.parseDouble(aux);}
        catch (NumberFormatException nfe){}
        pantalla.setText("");
        Resultado= Math.pow(Operan1,3);
        pantalla.setText(""+ Resultado);
        Operan1= Resultado;
        Opcional.setText("^3");
    }

    public void Elevado (View view){
        try{
            String cap = pantalla.getText().toString();
            Operan1 = Double.parseDouble(cap);
        }
        catch (NumberFormatException nfe){}
        pantalla.setText("");
        Operacion = "elevado";
        Opcional.setText(Operan1+"^");
    }

    public void Multiplicacion  (View view){
        try{
            String cap = pantalla.getText().toString();
            Operan1 = Double.parseDouble(cap);
        }catch (NumberFormatException nfe){}
        pantalla.setText("");
        Operacion = "multiplicacion";
        Opcional.setText(Operan1+"*");
    }

    public void Logaritmo (View view){
        try{
            String cap = pantalla.getText().toString();
            Operan1 = Double.parseDouble(cap);
        }
        catch (NumberFormatException nfe){}
        pantalla.setText("log "+Operan1);
        Operacion = "log";
    }

    public void LogaritmoN (View view){
        try{
            String cap = pantalla.getText().toString();
            Operan1 = Double.parseDouble(cap);
        }
        catch (NumberFormatException nfe){}
        pantalla.setText("ln "+Operan1);
        Operacion = "ln";
    }

    public void Igual (View view){

        try{String aux = pantalla.getText().toString();
            Operan2 = Double.parseDouble(aux);
        }
        catch (NumberFormatException nfe){}
        pantalla.setText("");

        if(Operacion == "suma"){
            Resultado = Operan1 + Operan2;
        }
        if(Operacion== "resta"){
            Resultado = Operan1 - Operan2;
        }
        if(Operacion== "multiplicacion"){
            Resultado = Operan1 * Operan2;
        }
        if(Operacion== "division"){

            if(Operan2 == 0.0){
                Opcional.setText("No se puede dividir entre 0");
            }
            else{
                Resultado = Operan1 / Operan2;
            }
        }
        if(Operacion== "raiz"){
            Resultado= Math.sqrt(Operan1);
        }
        if(Operacion=="elevado"){
            Resultado = Math.pow(Operan1,Operan2);
        }

        if(Operacion=="log"){
            Resultado = Math.log(Operan1);
        }
        if(Operacion=="ln"){
            Resultado = Math.log1p(Operan1);
        }


        pantalla.setText(""+Resultado);
        Operan1 = Resultado;
        Opcional.setText("");

    }

    public void Limpiar (View view){

        pantalla.setText("");
        Operan1 = 0.0;
        Operan2 = 0.0;
        Resultado = 0.0;
        Opcional.setText("");
    }



}
