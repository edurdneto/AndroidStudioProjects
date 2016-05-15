package br.ufc.lsbd.eduardo.tp07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver startService=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                StartedService service;
                context.startService(new Intent(context, StartedService.class));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Intent intent=new Intent();
        intent.setAction("br.ufc.dc.dspm.receiver.START_ACTION_SS");
        sendBroadcast(intent);*/
        registerReceiver(startService, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(startService, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    }

    public void apertei(View view){
        StartedService service;
        this.startService(new Intent(this, StartedService.class));
    }
}
