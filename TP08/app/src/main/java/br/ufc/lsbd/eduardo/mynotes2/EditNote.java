package br.ufc.lsbd.eduardo.mynotes2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditNote extends AppCompatActivity {
    public int pos;
    //public String title;
    //public String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        final EditText title=(EditText)findViewById(R.id.editTextTitle2);
        final EditText content=(EditText)findViewById(R.id.editTextContent2);
        final EditText data=(EditText)findViewById(R.id.editTextData2);
        pos=getIntent().getIntExtra("position",0);
        title.post(new Runnable() {
            @Override
            public void run() {
                title.setText(getIntent().getStringExtra("title"));
            }
        });
        content.post(new Runnable() {
            @Override
            public void run() {
                content.setText(getIntent().getStringExtra("content"));
            }
        });
        content.post(new Runnable() {
            @Override
            public void run() {
                data.setText(getIntent().getStringExtra("data"));
            }
        });
    }

    public void back(View view){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    public void update(View view){
        Intent intent = new Intent();
        intent.putExtra("position",pos);
        intent.putExtra("title",((EditText)findViewById(R.id.editTextTitle2)).getText().toString());
        intent.putExtra("content",((EditText)findViewById(R.id.editTextContent2)).getText().toString());
        intent.putExtra("data",((EditText)findViewById(R.id.editTextData2)).getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void apagar(View view){
        Intent intent = new Intent();
        intent.putExtra("position",pos);
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
