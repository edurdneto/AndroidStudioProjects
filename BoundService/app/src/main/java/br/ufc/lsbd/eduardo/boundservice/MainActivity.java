package br.ufc.lsbd.eduardo.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.ConnectionService;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import br.ufc.lsbd.eduardo.boundservice.LocalService.LocalBinder;

public class MainActivity extends AppCompatActivity {

    LocalService service;
    boolean isBound=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }

    public void onButtonClickSum(View view) {
        if (isBound) {
            EditText num1,num2;
            final TextView res;
            int i,j;
            num1=(EditText)findViewById(R.id.editText);
            num2=(EditText)findViewById(R.id.editText2);
            res=(TextView)findViewById(R.id.textView);
            i=Integer.parseInt(num1.getText().toString());
            j=Integer.parseInt(num2.getText().toString());
            final int num = service.sum(i,j);
            res.post(new Runnable() {
                @Override
                public void run() {
                    res.setText(Integer.toString(num));
                }
            });
        }
    }

    public void onButtonClickSub(View view) {
        if (isBound) {
            EditText num1,num2;
            final TextView res;
            int i,j;
            num1=(EditText)findViewById(R.id.editText);
            num2=(EditText)findViewById(R.id.editText2);
            res=(TextView)findViewById(R.id.textView);
            i=Integer.parseInt(num1.getText().toString());
            j=Integer.parseInt(num2.getText().toString());
            final int num = service.sub(i, j);
            res.post(new Runnable() {
                @Override
                public void run() {
                    res.setText(Integer.toString(num));
                }
            });
        }
    }

    public void onButtonClickMult(View view) {
        if (isBound) {
            EditText num1,num2;
            final TextView res;
            int i,j;
            num1=(EditText)findViewById(R.id.editText);
            num2=(EditText)findViewById(R.id.editText2);
            res=(TextView)findViewById(R.id.textView);
            i=Integer.parseInt(num1.getText().toString());
            j=Integer.parseInt(num2.getText().toString());
            final int num = service.mult(i, j);
            res.post(new Runnable() {
                @Override
                public void run() {
                    res.setText(Integer.toString(num));
                }
            });
        }
    }

    public void onButtonClickDiv(View view) {
        if (isBound) {
            EditText num1,num2;
            final TextView res;
            int i,j;
            num1=(EditText)findViewById(R.id.editText);
            num2=(EditText)findViewById(R.id.editText2);
            res=(TextView)findViewById(R.id.textView);
            i=Integer.parseInt(num1.getText().toString());
            j=Integer.parseInt(num2.getText().toString());
            final int num = service.div(i,j);
            res.post(new Runnable() {
                @Override
                public void run() {
                    res.setText(Integer.toString(num));
                }
            });
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBinder binder = (LocalBinder) service;
            MainActivity.this.service = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
