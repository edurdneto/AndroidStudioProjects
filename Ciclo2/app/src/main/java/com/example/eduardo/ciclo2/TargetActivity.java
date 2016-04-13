package com.example.eduardo.ciclo2;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class TargetActivity extends AppCompatActivity {
    private static final String CATEGORIA = "CicloVida";
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        Log.i(CATEGORIA, getLocalClassName() + "onStart()->Started");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       Log.i(CATEGORIA, getLocalClassName() + "onStop()->Stoped");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    protected String getClassName() {
        return TargetActivity.class.getName();
    }


    public void ok(View view){
        setResult(RESULT_OK);
        finish();
    }
    public void cancela(View vie){
        setResult(RESULT_CANCELED);
        finish();
    }
}
