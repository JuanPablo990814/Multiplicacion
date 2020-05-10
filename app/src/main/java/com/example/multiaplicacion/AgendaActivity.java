package com.example.multiaplicacion;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multiaplicacion.utilidades.Utilidades;

public class AgendaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView titulo;
    private Typeface daywalker;
    private Spinner Tipo;
    private TextView Mirar;
    private EditText Nombre,Apellido,Email,Cedula;
    private Button Guardar,Buscar;
    private String Tipocontacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        //ActionBar Mostrar Icono al lado del nombre
        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_contactoss);

        String fuente = "fuentes/Daywalker.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        final ConexionSQLiteHelper conn=new ConexionSQLiteHelper(AgendaActivity.this,"bd_usuarios",null,1);

        Nombre =(EditText)findViewById(R.id.edNombre);
        Apellido = (EditText)findViewById(R.id.edApellido);
        Email= (EditText)findViewById(R.id.edEmail);
        Cedula=(EditText)findViewById(R.id.edCedula);
        Guardar = (Button)findViewById(R.id.btnGuardar);
        Buscar = (Button)findViewById(R.id.btnBuscar);

        titulo = (TextView)findViewById(R.id.textView);
        titulo.setTypeface(daywalker);

        Tipo =(Spinner)findViewById(R.id.spTipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Tiposcontactos,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Tipo.setAdapter(adapter);
        Tipo.setOnItemSelectedListener(this);

        Mirar = (TextView)findViewById(R.id.tvMirar);

        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros={Cedula.getText().toString()};
                String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_APELLIDO,Utilidades.CAMPO_TIPO,Utilidades.CAMPO_EMAIL};

                try{
                    Cursor cursor = db.query(Utilidades.TABLA_USUARIO2,campos,Utilidades.CAMPO_CEDULA+"=?",parametros,null,null,null);
                    cursor.moveToFirst();
                    Nombre.setText(cursor.getString(0));
                    Apellido.setText(cursor.getString(1));
                    Mirar.setText(cursor.getString(2));
                    Email.setText(cursor.getString(3));
                    cursor.close();
                }catch (Exception e){
                    Toast.makeText(AgendaActivity.this, "La Cedula no existe en la base de datos", Toast.LENGTH_SHORT).show();
                    Limpiar();
                }
            }

            private void Limpiar() {
                Nombre.setText("");
                Apellido.setText("");
                Mirar.setText("");
                Nombre.setText("");
            }
        });


        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarContacto();
            }

            private void GuardarContacto() {

                SQLiteDatabase db= conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_CEDULA,Cedula.getText().toString());
                values.put(Utilidades.CAMPO_NOMBRE,Nombre.getText().toString());
                values.put(Utilidades.CAMPO_APELLIDO,Apellido.getText().toString());
                values.put(Utilidades.CAMPO_TIPO,Tipocontacto);
                values.put(Utilidades.CAMPO_EMAIL,Email.getText().toString());

                Long idResultante=db.insert(Utilidades.TABLA_USUARIO2,Utilidades.CAMPO_CEDULA,values);

                Toast.makeText(AgendaActivity.this, "ID REGISTRO: "+idResultante, Toast.LENGTH_SHORT).show();
                db.close();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String Texto = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, ""+Texto, Toast.LENGTH_SHORT).show();
        Tipocontacto=Texto;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
