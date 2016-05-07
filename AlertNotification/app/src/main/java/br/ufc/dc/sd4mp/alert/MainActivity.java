package br.ufc.dc.sd4mp.alert;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*CheckBox chk = (CheckBox)findViewById(R.id.checkBox);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setContentView(R.layout.activity_main);
                    Intent intent=new Intent();
                    intent.setAction("Intent.ACTION_AIRPLANE_MODE_CHANGED");
                    sendBroadcast(intent);
                }
            }
        });*/
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

    public void send(View view){
        CheckBox chk1 = (CheckBox)findViewById(R.id.checkBox);
        CheckBox chk2 = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox chk3 = (CheckBox)findViewById(R.id.checkBox3);
        CheckBox chk4 = (CheckBox)findViewById(R.id.checkBox4);

        if(chk1.isChecked())battery_checked(view);
        if(chk2.isChecked())airplane_checked(view);
        if(chk3.isChecked())power_connected_checked(view);
        if(chk4.isChecked())power_disconnected_checked(view);
    }

    public void airplane_checked(View view){
        setContentView(R.layout.activity_main);
        Intent intent=new Intent();
        intent.setAction("Intent.ACTION_AIRPLANE_MODE_CHANGED");
        sendBroadcast(intent);
    }

    public void battery_checked(View view){
        setContentView(R.layout.activity_main);
        Intent intent=new Intent();
        intent.setAction("Intent.ACTION_BATTERY_CHANGED");
        sendBroadcast(intent);
    }

    public void power_connected_checked(View view){
        setContentView(R.layout.activity_main);
        Intent intent=new Intent();
        intent.setAction("Intent.ACTION_POWER_CONNECTED");
        sendBroadcast(intent);
    }

    public void power_disconnected_checked(View view){
        setContentView(R.layout.activity_main);
        Intent intent=new Intent();
        intent.setAction("Intent.ACTION_POWER_DISCONNECTED");
        sendBroadcast(intent);
    }
}
