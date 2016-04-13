package com.example.eduardo.ciclo2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class SourceActivity extends Activity {

    private static final int RESPOSTA = 2;
    private static final String CATEGORIA = "CicloVida";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(CATEGORIA, getLocalClassName() + "onDestroy()->Destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(CATEGORIA, getLocalClassName() + "onPause()->Paused");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(CATEGORIA, getLocalClassName() + "onRestart()->Restarted");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(CATEGORIA, getLocalClassName() + "onResume()->Resumed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Log.i(CATEGORIA, getLocalClassName() + "onStart()->Started");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Source Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.eduardo.ciclo2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Source Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.eduardo.ciclo2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        Log.i(CATEGORIA, getLocalClassName() + "onStop()->Stoped");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    protected String getClassName() {
        return SourceActivity.class.getName();
    }


    public void startActivityExplicita(View view) {
        Intent intent = new Intent(SourceActivity.this, TargetActivity.class);
        startActivityForResult(intent, RESPOSTA);
    }

    public void startActivityImplicita(View view) {
        Intent intent = new Intent();
        intent.setAction("br.ufc.dc.dspm.action.Acao");
        intent.setComponent(null);
        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
        startActivityForResult(intent,RESPOSTA);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        TextView t = new TextView(this);
        if (requestCode == RESPOSTA) {
            if (resultCode == RESULT_OK) {
                t=(TextView)findViewById(R.id.textView2);
                t.setText("O  usuário  clicou  em  CONFIRMA");
                t.setVisibility(View.VISIBLE);
                Log.i(CATEGORIA,getClassName()+"O  usuário  clicou  em  CONFIRMA");
            }
            if (resultCode == RESULT_CANCELED) {
                t=(TextView)findViewById(R.id.textView2);
                t.setText("O  usuário  clicou  em  Cancela");
                t.setVisibility(View.VISIBLE);
                Log.i(CATEGORIA, getClassName() + "O  usuário  clicou  em  CANCELA");
            }
        }
    }

}
