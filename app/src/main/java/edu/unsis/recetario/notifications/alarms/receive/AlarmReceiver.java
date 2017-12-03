package edu.unsis.recetario.notifications.alarms.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Meltsan on 02/12/17.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"test alarm",Toast.LENGTH_SHORT).show();
    }
}
