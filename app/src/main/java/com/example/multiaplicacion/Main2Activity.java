package com.example.multiaplicacion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Notification.*;

public class Main2Activity extends AppCompatActivity {

    private Button Iniciar;
    private TextView Temporizador;
    private EditText Tiempo;
    private CountDownTimer cuentaRegresiva;
    private long tiempoMilisegundos;
    private Boolean Corriendo = false;
    private Button Prueba;

    NotificationCompat.Builder mBuilder;
    int mNotificacionId=1;
    String channelId = "mi_canal_id_01";

    private TextView titulo;
    private Typeface daywalker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Iniciar = (Button)findViewById(R.id.btnIniciarT);
        Temporizador = (TextView)findViewById(R.id.txvTempo);
        Tiempo = (EditText)findViewById(R.id.etTiempo);
        Prueba = (Button)findViewById(R.id.btnPrueba);

        Tiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tiempo.setText("");
            }
        });

        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertirAMil();
                IniciarDetenerse();
            }
        });
        Prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificacion8();
            }

        });

        //ActionBar Mostrar Icono al lado del nombre|
        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_relojj);

        String fuente = "fuentes/Daywalker.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);

        titulo = (TextView)findViewById(R.id.textView9);
        titulo.setTypeface(daywalker);

    }


    private void Notificacion8(){
        NotificationManager mNotifyManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        mBuilder= new NotificationCompat.Builder(Main2Activity.this,null);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence nombre= "Prueba";
            String descripcion = "prueba a ver si porfin";
            int importancia = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId,nombre,importancia);
            //configuracion del canal
            mChannel.setDescription(descripcion);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100,200});
            mNotifyManager.createNotificationChannel(mChannel);
            mBuilder = new NotificationCompat.Builder(Main2Activity.this,channelId);

            mBuilder.setSmallIcon(R.mipmap.ic_relojj);
            mBuilder.setContentTitle("TEMPORIZADOR");
            mBuilder.setContentText("Tiempo finalizado, Prueba de Notificacion");
            mBuilder.setVibrate(new long[] {100, 250, 100, 500});
            mBuilder.setAutoCancel(true);
            mBuilder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.slip));
            Intent resultIntent = new Intent(Main2Activity.this,MainActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(Main2Activity.this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            mBuilder.setChannelId(channelId);
            mNotifyManager.notify(mNotificacionId,mBuilder.build());
        }
    }

    private void ConvertirAMil(){
        int Time = Integer.parseInt(Tiempo.getText().toString());
        Time=(Time+1)*1000;
        tiempoMilisegundos=Time;
    }

    private void IniciarDetenerse() {
        if(Corriendo){
            Detener();
        }
        else{
            Iniciar();
        }
    }
    private void Iniciar(){
        cuentaRegresiva = new CountDownTimer(tiempoMilisegundos,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoMilisegundos = millisUntilFinished;
                actualizarTimer();
            }

            @Override
            public void onFinish() {
                Notificacion8();
            }
        }.start();
        Iniciar.setText("Reiniciar");
        Corriendo = true;
    }

    private void actualizarTimer() {
        int minutos = (int) tiempoMilisegundos/60000;
        int segundos = (int) tiempoMilisegundos % 60000 / 1000;
        String tiempoTexto;
        tiempoTexto = ""+minutos+":";
        tiempoTexto+=segundos<10?"0"+segundos:""+segundos;

        Temporizador.setText(tiempoTexto);
    }
    private void Detener(){
        cuentaRegresiva.cancel();
        Corriendo = false;
        Iniciar.setText("Iniciar");
    }
}
