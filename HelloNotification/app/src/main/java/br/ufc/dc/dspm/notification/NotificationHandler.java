package br.ufc.dc.dspm.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class NotificationHandler extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_handler);
        Context context = getApplicationContext();
        NotificationManager manager;
        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(R.string.app_name);
        CharSequence text = "Notification Handled!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
