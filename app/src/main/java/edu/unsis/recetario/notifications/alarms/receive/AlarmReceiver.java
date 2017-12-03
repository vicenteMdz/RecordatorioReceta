package edu.unsis.recetario.notifications.alarms.receive;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import edu.unsis.recetario.treatements.AddTreatement;

/**
 * Created by Meltsan on 02/12/17.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        /******** crear la notificación a lanzar *******/
        NotificationManager nManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        // Creamos el Intent que llamará a nuestra Activity
        Intent targetIntent = new Intent(context,
                AddTreatement.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setTicker("MediReminder")
                .setContentTitle("Nuevo medicamento por tomar")
                .setContentText("Hola, xxx, tienes un medicamento programado para ser tomado a esta hora")
                .setVibrate(new long[] {100, 250, 100, 500})
                .setSound(uri)
                .setWhen(System.currentTimeMillis());


        builder.setContentIntent(contentIntent);

        builder.setAutoCancel(true);

        nManager.notify(123456, builder.build());
    }
}
