package br.ufc.dc.dspm.m0337526;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String CATEGORIA = "CicloVida";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();
        EditText edi=(EditText)findViewById(R.id.editText);
        edi.setText("");
        edi.setHint("id");
    }

    public void ok(View view){
        Intent intent = new Intent();
        intent.setAction("br.ufc.dc.dspm.action.tweetsActivity");
        intent.setComponent(null);
        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
        Bundle b=new Bundle();
        b.putString("id", ((EditText)findViewById(R.id.editText)).getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }
}
