package com.ufc.lsbd.eduardo.tutorial3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginOkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ok);
        Bundle b=new Bundle();
        b=getIntent().getExtras();
        TextView t=new TextView(this);
        t=(TextView)findViewById(R.id.textView3);
        t.setText("Caro(a) "+b.getString("name")+", seu login foi realizado com suscesso!");
        t.setVisibility(View.VISIBLE);
    }

    public void ok(View view){
        finish();
    }
}
