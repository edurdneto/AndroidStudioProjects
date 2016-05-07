package com.ufc.dc.sd4mp.mymail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendEmail(View view) {
        Intent intent = new Intent();
        intent.setAction("br.ufc.dc.dspm.action.AcaoLinearActivity");
        intent.setComponent(null);
        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
        startActivity(intent);
    }

    public void sairDoMymail(View view){
        finish();
    }
}
