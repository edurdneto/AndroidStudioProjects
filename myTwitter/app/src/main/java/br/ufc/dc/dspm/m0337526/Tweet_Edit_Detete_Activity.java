package br.ufc.dc.dspm.m0337526;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Tweet_Edit_Detete_Activity extends AppCompatActivity {
    public int pos;
    public String texto;
    String t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets_activitys);
        t=getIntent().getStringExtra("tweet");
        pos = getIntent().getIntExtra("position", 0);
        final EditText editText=(EditText)findViewById(R.id.editText3);
        editText.post(new Runnable() {
            @Override
            public void run() {
                editText.setText(t);
            }
        });

    }

    public void update(View view){
        Intent intent = new Intent();
        intent.putExtra("pos",pos);
        texto=((EditText)findViewById(R.id.editText3)).getText().toString();
        intent.putExtra("tweet",texto);
        setResult(RESULT_OK,intent);
        finish();
    }

    public void delete(View v){
        Intent intent = new Intent();
        intent.putExtra("pos",pos);
        setResult(RESULT_CANCELED,intent);
        finish();
    }
}
