package com.example.multiaplicacion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CalendarioActivity extends AppCompatActivity {

    private TextView titulocal;
    private Typeface daywalker;

    private EditText Texto;
    private DatePicker Fecha;
    private Button Guardar;
    private CountDownTimer cuentaRegresiva;

    NotificationCompat.Builder mBuilder;
    int mNotificacionId=1;
    String channelId = "mi_canal_id_02";

    Calendar calendario= Calendar.getInstance();
    private double tiempoMilisegundos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_calendario);

        String fuente = "fuentes/Daywalker.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);

        titulocal=(TextView)findViewById(R.id.textView3);
        titulocal.setTypeface(daywalker);

        Texto = (EditText)findViewById(R.id.etTexto);
        Fecha = (DatePicker)findViewById(R.id.dpFecha);
        Guardar= (Button)findViewById(R.id.btnGuardar);

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dia=Fecha.getDayOfMonth();
                int diaactual = calendario.get(Calendar.DAY_OF_MONTH);
                if(dia==diaactual){
                    Notificacion8();
                }
                else{
                    double Time;
                    dia=dia-diaactual;
                    Time=dia*8.64e+7;
                    tiempoMilisegundos=Time;
                    cuentaRegresiva = new CountDownTimer((long) tiempoMilisegundos,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            tiempoMilisegundos = millisUntilFinished;
                        }

                        @Override
                        public void onFinish() {
                            Notificacion8();
                        }
                    }.start();
                    Toast.makeText(CalendarioActivity.this, "Alarma Agregada", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void Iniciar(){

    }
    private void Notificacion8(){
        NotificationManager mNotifyManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        mBuilder= new NotificationCompat.Builder(CalendarioActivity.this,null);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence nombre= "Recordatorio";
            String descripcion = "Recordar: "+ Texto.getText().toString();
            int importancia = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId,nombre,importancia);
            //configuracion del canal
            mChannel.setDescription(descripcion);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100,200,300,400,500});
            mNotifyManager.createNotificationChannel(mChannel);
            mBuilder = new NotificationCompat.Builder(CalendarioActivity.this,channelId);

            mBuilder.setSmallIcon(R.mipmap.ic_relojj);
            mBuilder.setContentTitle("RECORDATORIO");
            mBuilder.setContentText("Recordar: "+ Texto.getText().toString());
            mBuilder.setVibrate(new long[] {100,200,300,500});
            mBuilder.setAutoCancel(true);
            mBuilder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.slip));
            Intent resultIntent = new Intent(CalendarioActivity.this,MainActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(CalendarioActivity.this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            mBuilder.setChannelId(channelId);
            mNotifyManager.notify(mNotificacionId,mBuilder.build());
        }
    }
}
