package br.ufc.lsbd.eduardo.tp07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/**
 * Created by eduardo on 2016-05-10.
 */
public class StartService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            StartedService service;
            context.startService(new Intent(context, StartedService.class));
        }
    }
}
