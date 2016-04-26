package br.ufc.dc.dspm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by eduardo on 2016-04-26.
 */
public class ShowToastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        CharSequence text = " Broadcast Received !";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
