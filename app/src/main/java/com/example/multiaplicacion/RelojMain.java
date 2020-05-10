package com.example.multiaplicacion;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.nio.channels.InterruptedByTimeoutException;
import java.nio.channels.InterruptibleChannel;
import java.util.*;

//para hacer los reloj en tiempo real toca hacer un hilo y impremetar el metodo runnable en la clase publica para poder usarlo
public class RelojMain extends AppCompatActivity implements Runnable {


    private TextView titulo;
    private Typeface daywalker;

    private Button AgregarAlarma;
    private ImageButton AbrirVentana;


    //Strings de la hora actual
    String horaA,minutosA,segundosA,ampmA;
    //String Hora Buenos Aires
    String horaBA,minutosBA,segundosBA,ampmBA;
    //String Hora Japon
    String horaJ,minutosJ,segundosJ,ampmJ;
    //String Hora Rusia
    String horaR,minutosR,segundosR,ampmR;
    //String Hora Maldivas
    String horaM,minutosM,segundosM,ampmM;
    //String Hora California
    String horaC,minutosC,segundosC,ampmC;
    //String Hora Londres
    String horaL,minutosL,segundosL,ampmL;
    Thread h1 = new Thread(this);

    private TextView editHora;
    private EditText HoraAlarma;
    private static EditText DiaAlarma;
    private Button AgAlarma;
    TimePickerDialog timePickerDialog;
    //HORA PAISES
    public TextView BuenosAires;
    private TextView Japon,Rusia,Maldivas,California,Londres;


    NotificationCompat.Builder notificacion;
    private static final int idUnica = 51423;

    //Cronometro Var
    private Button Iniciar,Detener,Reiniciar;
    private Chronometer Cronometro;
    private Boolean Correr=false;
    private long Detenerse;

    NotificationCompat.Builder mBuilder;
    int mNotificacionId=2;
    String channelId = "mi_canal_id_02";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reloj_main);

        ActionBar mibarra = getSupportActionBar();
        mibarra.setDisplayShowHomeEnabled(true);
        mibarra.setIcon(R.mipmap.ic_relojj);

        String fuente = "fuentes/Daywalker.ttf";
        this.daywalker = Typeface.createFromAsset(getAssets(),fuente);

        titulo = (TextView)findViewById(R.id.textView);
        titulo.setTypeface(daywalker);

        //Alarma
        HoraAlarma = (EditText)findViewById(R.id.etHoraAlarma);
        DiaAlarma = (EditText)findViewById(R.id.etDiaAlarma);
        AgAlarma = (Button)findViewById(R.id.btnAgregar);

        //Dia Mes Año de la alarma

        DiaAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog(DiaAlarma);
            }
        });


        AgregarAlarma = (Button)findViewById(R.id.btnAgregar);
        AgregarAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notificacion8();
            }
        });
        // HORA DE LA ALARMA
        AgAlarma =(Button)findViewById(R.id.btnAgregar);
        AgAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        HoraAlarma .setOnClickListener(new View.OnClickListener() {
            Calendar calendarioHora = Calendar.getInstance();
            @Override
            public void onClick(View v) {

                int hour = calendarioHora.get(Calendar.HOUR);
                int minute = calendarioHora.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(RelojMain.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        String am_pm = null;
                        String minutoString;


                        minutoString = minute>9?""+minute:"0"+minute;
                        if(hour<12){
                            am_pm = "AM";
                            if(hour<=9){
                                HoraAlarma.setText("0"+hour+":"+minutoString+" "+am_pm);
                            }else{
                                HoraAlarma.setText(hour+":"+minutoString+" "+am_pm);
                            }

                        }else if(hour==12){
                            am_pm="PM";
                            HoraAlarma.setText(hour+":"+minutoString+" "+am_pm);
                        }else{
                            am_pm="PM";
                            HoraAlarma.setText(hour+":"+minutoString+" "+am_pm);
                        }
                    }
                },hour,minute,false);

                timePickerDialog.show();
            }
        });

        //Ventana
        AbrirVentana = (ImageButton)findViewById(R.id.btnActivity2);
        AbrirVentana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ntAbrirVentana();
            }
        });

        //Reloj
        editHora = (TextView)findViewById(R.id.txvHora);
        h1 = new Thread();
        h1.start();


        //OTROS PAISES
        //BuenosAires,Japon,Rusia,Maldivas,California,Londres
        BuenosAires = (TextView)findViewById(R.id.txvBuenos);
        Japon = (TextView)findViewById(R.id.txvJapon);
        Rusia = (TextView)findViewById(R.id.txvRusia);
        Maldivas = (TextView)findViewById(R.id.txvMaldi);
        California = (TextView)findViewById(R.id.txvCali);
        Londres = (TextView)findViewById(R.id.txvLondres);

        //CRONOMETRO

        Iniciar = (Button)findViewById(R.id.btnStart);
        Detener = (Button)findViewById(R.id.btnStop);
        Reiniciar = (Button)findViewById(R.id.btnReiniciar);
        Cronometro = (Chronometer) findViewById(R.id.Crono);

        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCronometro();
            }
        });

        Detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCronometro();
            }
        });

        Reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarCronometro();
            }
        });

        h1 = new Thread(this);
        h1.start();
    }

    @Override
    public void run() {
        //hilo en tiempo real
        Thread ct = Thread.currentThread();
        while(ct==h1){
            calcula();
            editHora.setText(horaA+":"+minutosA+":"+segundosA+" "+ampmA);
            BuenosAiresCalc();
            BuenosAires.setText(horaBA+":"+minutosBA+":"+segundosBA+" "+ampmBA);
            JaponCalc();
            Japon.setText(horaJ+":"+minutosJ+":"+segundosJ+" "+ampmJ);
            RusiaCalc();
            Rusia.setText(horaR+":"+minutosR+":"+segundosR+" "+ampmR);
            MaldivasCalc();
            Maldivas.setText(horaM+":"+minutosM+":"+segundosM+" "+ampmM);
            LondresCalc();
            Londres.setText(horaL+":"+minutosL+":"+segundosL+" "+ampmL);
            CaliforniaCalc();
            California.setText(horaC+":"+minutosC+":"+segundosC+" "+ampmC);

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){}
        }

    }

    //DIA MES AÑO DE LA ALARMA

    //---------------------------------------------------------------------------------------------------------------

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);

        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            final String miFecha = day+"/"+(month+1)+"/"+year;
            DiaAlarma.setText(miFecha);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    private void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        ampmA=calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

        if(ampmA.equals("PM")){
            int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
            horaA = h>9?""+h:"0"+h;
        }else{
            horaA=calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutosA = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundosA = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);

    }
    private void BuenosAiresCalc(){
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        int horaArgenta = calendario.get(Calendar.HOUR_OF_DAY)+2;

        ampmBA =horaArgenta>12?"PM":"AM";

        if(ampmBA.equals("PM")){
            int h = horaArgenta-12;
            horaBA = h>9?""+h:"0"+h;
        }else{
            horaBA=horaArgenta>9?""+horaArgenta:"0"+horaArgenta;
        }
        minutosBA = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundosBA = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }
    private void JaponCalc(){
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        int horaJaponesa = calendario.get(Calendar.HOUR_OF_DAY)-10;

        ampmJ =horaJaponesa>12?"PM":"AM";

        if(ampmJ.equals("PM")){
            int h = horaJaponesa-12;
            horaJ = h>9?""+h:"0"+h;
        }else{
            horaJ=horaJaponesa>9?""+horaJaponesa:"0"+horaJaponesa;
        }
        minutosJ = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundosJ = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }

    private void RusiaCalc(){
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        int horaRusa = calendario.get(Calendar.HOUR_OF_DAY)-16;

        ampmR =horaRusa>12?"PM":"AM";

        if(ampmR.equals("PM")){
            int h = horaRusa-12;
            horaR = h>9?""+h:"0"+h;
        }else{
            horaR=horaRusa>9?""+horaRusa:"0"+horaRusa;
        }
        minutosR = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundosR = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }
    private void MaldivasCalc(){
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        int horaMaldiva = calendario.get(Calendar.HOUR_OF_DAY)-14;

        ampmM =horaMaldiva>12?"PM":"AM";

        if(ampmM.equals("PM")){
            int h = horaMaldiva-12;
            horaM = h>9?""+h:"0"+h;
        }else{
            horaM=horaMaldiva>9?""+horaMaldiva:"0"+horaMaldiva;
        }
        minutosM = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundosM = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }

    private void LondresCalc(){
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        int horaInglesa = calendario.get(Calendar.HOUR_OF_DAY)-18;

        ampmL =horaInglesa>12?"PM":"AM";

        if(ampmL.equals("PM")){
            int h = horaInglesa-12;
            horaL = h>9?""+h:"0"+h;
        }else{
            horaL=horaInglesa>9?""+horaInglesa:"0"+horaInglesa;
        }
        minutosL = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundosL = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }

    private void CaliforniaCalc(){
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        int horaEEUU = calendario.get(Calendar.HOUR_OF_DAY)-18;

        ampmC =horaEEUU>12?"PM":"AM";

        if(ampmC.equals("PM")){
            int h = horaEEUU-12;
            horaC = h>9?""+h:"0"+h;
        }else{
            horaC=horaEEUU>9?""+horaEEUU:"0"+horaEEUU;
        }
        minutosC = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundosC = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }

    //---------------------------------------------------------------------------------------------------------------

    public void startCronometro(){
        if(Correr==false){
            Cronometro.setBase(SystemClock.elapsedRealtime()-Detenerse);
            Cronometro.start();
            Correr=true;
        }

    }

    public void stopCronometro(){
        if(Correr==true){
            Cronometro.stop();
            Detenerse = SystemClock.elapsedRealtime() - Cronometro.getBase();
            Correr=false;
        }

    }

    private void reiniciarCronometro() {
        Cronometro.setBase(SystemClock.elapsedRealtime());
        Detenerse=0;
    }

    private void ntAbrirVentana(){
        Intent intent = new Intent(RelojMain.this,Main2Activity.class);

        startActivity(intent);
    }
    private void Notificacion8(){
        NotificationManager mNotifyManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        mBuilder= new NotificationCompat.Builder(RelojMain.this,null);
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
            mBuilder = new NotificationCompat.Builder(RelojMain.this,channelId);

            mBuilder.setSmallIcon(R.mipmap.ic_relojj);
            mBuilder.setContentTitle("TEMPORIZADOR");
            mBuilder.setContentText("Tiempo finalizado, Prueba de Notificacion");
            mBuilder.setVibrate(new long[] {100, 250, 100, 500});
            mBuilder.setAutoCancel(true);
            Intent resultIntent = new Intent(RelojMain.this,RelojMain.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(RelojMain.this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);

            mBuilder.setChannelId(channelId);
            mNotifyManager.notify(mNotificacionId,mBuilder.build());
        }
    }

    private void AgregarAlarma(String mensajeap,int horaap,int minutosap){

    }
}
