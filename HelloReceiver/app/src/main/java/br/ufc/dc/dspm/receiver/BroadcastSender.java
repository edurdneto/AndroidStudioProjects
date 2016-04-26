package br.ufc.dc.dspm.receiver;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BroadcastSender extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_sender);
        Intent intent=new Intent();
        intent.setAction("br.ufc.dc.dspm.receiver.START_ACTION");
        sendBroadcast(intent);
    }
}
