package com.ufc.lsbd.eduardo.tutorial3linearlayout;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginLayoult extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layoult);
    }

    public void startActivityImplicita(View view) {
        EditText name=(EditText)findViewById(R.id.editText);
        EditText pass=(EditText)findViewById(R.id.editText2);

        Intent intent = new Intent();
        intent.setAction("br.ufc.dc.dspm.action.AcaoLinearActivity");
        intent.setComponent(null);
        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
        Bundle b=new Bundle();
        b.putString("name",name.getText().toString());
        b.putString("pass",pass.getText().toString());
        name.setText("");
        pass.setText("");
        intent.putExtras(b);
        startActivity(intent);
    }

    public void Cancela(View view){
        finish();
    }
}
