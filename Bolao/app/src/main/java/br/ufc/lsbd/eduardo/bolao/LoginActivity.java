package br.ufc.lsbd.eduardo.bolao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view){
        Intent intent =new Intent();
        intent.setAction("br.ufc.dc.dspm.action.loginOk");
        intent.setComponent(null);
        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
        startActivity(intent);
    }


}
