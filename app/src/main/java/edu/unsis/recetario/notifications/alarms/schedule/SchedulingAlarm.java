package edu.unsis.recetario.notifications.alarms.schedule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.unsis.recetario.medicines.model.Medicamento;

/**
 * Created by Meltsan on 02/12/17.
 * Clase que implementa el método para crear una alarma dependiendo de las opciones
 * dadas por el usuario en el alta de tratamientos
 */
public class SchedulingAlarm {

    private Context context;
    private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public SchedulingAlarm(Context context) {
        this.context = context;
    }

    public void createAlarm(Medicamento medicamento, Intent intent, PendingIntent pendingIntent) throws Exception{
        Log.d("createAlarm","Configurando alarma");
                /* Retrieve a PendingIntent that will perform a broadcast */
        //Intent alarmIntent = new Intent(Home.this, AlarmReceiver.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(Home.this, 0, alarmIntent, 0);
        Calendar fechaInicioToma = Calendar.getInstance();
        try{
            AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
            //iniciamos el proceso en la fecha y hora especificada por el usuario
            SimpleDateFormat sdf= new SimpleDateFormat(DATEFORMAT);
            fechaInicioToma.setTime(sdf.parse(medicamento.getFechaInicio() + " " + medicamento.getHoraInicio()));
            if("M".equals(medicamento.getTipoPeriodoToma())){
                /* Repitiendo alarma cada X MINUTOS */
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, fechaInicioToma.getTimeInMillis(),
                        1000 * 60 * medicamento.getPeriodoToma(), pendingIntent);
            } else if( "H".equals(medicamento.getTipoPeriodoToma())){
                /* Repitiendo alarma cada X HORAS */
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, fechaInicioToma.getTimeInMillis(),
                        AlarmManager.INTERVAL_HOUR * medicamento.getPeriodoToma(), pendingIntent);
            } else if( "D".equals(medicamento.getTipoPeriodoToma())){
                /* Repitiendo alarma cada X DIAS */
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, fechaInicioToma.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY * medicamento.getPeriodoToma(), pendingIntent);
            }
        }catch (Exception e){
            Log.d("ErrorCreateDate",e.getCause().getMessage());
            throw new Exception(e);
        }
    }

    public void cancelAlarm(PendingIntent pendingIntent){
        AlarmManager manager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Log.d("cancelAlarm","Alarma Cancelada");
    }

    private long getinterval(int minutos, int horas){
        return 1000 * 60 * minutos * horas;
    }
}
