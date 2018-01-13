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
import edu.unsis.recetario.notifications.dao.NotificacionesDAOImpl;
import edu.unsis.recetario.notifications.model.Notificacion;

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

    public void createAlarm(Medicamento medicamento, PendingIntent pendingIntent, String alarmId) throws Exception{
        Log.d("createAlarm","Configurando alarma***");
        Calendar fechaInicioToma = Calendar.getInstance();
        try{
            AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
            //iniciamos el proceso en la fecha y hora especificada por el usuario
            SimpleDateFormat sdf= new SimpleDateFormat(DATEFORMAT);
            fechaInicioToma.setTime(sdf.parse(medicamento.getFechaInicio() + " " + medicamento.getHoraInicio()+":00"));
            Log.d("fecha", fechaInicioToma.toString());
            if("Minutos".equals(medicamento.getTipoPeriodoToma())){
                /* Repitiendo alarma cada X MINUTOS */
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, fechaInicioToma.getTimeInMillis(),
                        1000 * 60 * medicamento.getPeriodoToma(), pendingIntent);
            } else if( "Horas".equals(medicamento.getTipoPeriodoToma())){
                /* Repitiendo alarma cada X HORAS */
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, fechaInicioToma.getTimeInMillis(),
                        AlarmManager.INTERVAL_HOUR * medicamento.getPeriodoToma(), pendingIntent);
            } else if( "Dias".equals(medicamento.getTipoPeriodoToma())){
                /* Repitiendo alarma cada X DIAS */
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, fechaInicioToma.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY * medicamento.getPeriodoToma(), pendingIntent);
            }
            Notificacion n = new Notificacion();
            n.setFecha(medicamento.getFechaInicio());
            n.setIntentId(alarmId);
            n.setSw_tomado("P");
            n.setIdMedicamento(medicamento.getIdMedicamento());
            Date dateInitial = sdf.parse(medicamento.getFechaInicio() + " " + medicamento.getHoraInicio()+":00");
            Calendar c = Calendar.getInstance();
            NotificacionesDAOImpl notificacionesDAO = new NotificacionesDAOImpl(context);
            for(int i=0;i<5;i++){
                dateInitial = sumarMinutos(dateInitial,medicamento.getPeriodoToma());
                c.setTime(dateInitial);
                String hora = ""+c.get(Calendar.HOUR_OF_DAY)+":"+Calendar.MINUTE+":"+Calendar.SECOND;
                n.setHora(hora);
                notificacionesDAO.insertNotification(n);
            }
        }catch (Exception e){
            e.printStackTrace();
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

    public void calculaTomas(){

    }

    public Date sumarMinutos(Date fecha, int minutos){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MINUTE, minutos);
        return calendar.getTime();
    }

    public Date sumarHoras(Date fecha, int horas){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.HOUR, horas);
        return calendar.getTime();
    }

    public Date sumarDias(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public Date sumarSemanas(Date fecha, int semanas){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.WEEK_OF_YEAR, semanas);
        return calendar.getTime();
    }

    public Date sumarMeses(Date fecha, int meses){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, meses);
        return calendar.getTime();
    }

    public Date sumarAnios(Date fecha, int anios){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.YEAR, anios);
        return calendar.getTime();
    }
}
