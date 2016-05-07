package com.ufc.dc.sd4mp.mymail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EmailSend extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_send);
    }

    public void enviarMail(View view){

        String dest = new String();
        String subject = new String();
        String content = new String();
        dest=((EditText)findViewById(R.id.editText)).getText().toString();
        subject=((EditText)findViewById(R.id.editText2)).getText().toString();
        content=((EditText)findViewById(R.id.editText3)).getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] {dest});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, content);
        email.setType("plain/text");
        startActivity(
                Intent.createChooser
                        (email, "Sending mail..."));
    }


    public void cancelar(View view){
        Context context = getApplicationContext();
        CharSequence text = "Email Descartado!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        finish();
    }
}
