package br.ufc.dc.sd4mp.alert;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by eduardo on 2016-04-30.
 */
public class Airplane_Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent){
        showNotification(context);
    }

    private void showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.airplane_mode_off);
        builder.setContentTitle("Airplane");
        builder.setContentText("Mode!");
        builder.setContentIntent(contentIntent);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, builder.build());
    }
}
