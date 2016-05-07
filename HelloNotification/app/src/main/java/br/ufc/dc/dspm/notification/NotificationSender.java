package br.ufc.dc.dspm.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NotificationSender extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sender);
    }

    protected void createNotification(String status,String title,String content){
        NotificationManager manager;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker(status);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.android_icon);
        Intent intent = new Intent(this, NotificationHandler.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        manager.notify(R.string.app_name, notification);
    }

    public void apertar(View view){

        createNotification("OnLine","Notification","Atencao");
    }
}
