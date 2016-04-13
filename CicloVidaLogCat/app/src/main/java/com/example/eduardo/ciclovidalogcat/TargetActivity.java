package com.example.eduardo.ciclovidalogcat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by eduardo on 2016-04-07.
 */
public class TargetActivity extends AppCompatActivity {
    private static final String CATEGORIA = "CicloVida";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target_layout);

        Log.i(CATEGORIA, getClassName() + "onCreate()->Created");


    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(CATEGORIA, getLocalClassName() + "onDestroy()->Destroyed");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(CATEGORIA, getLocalClassName() + "onPause()->Paused");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(CATEGORIA, getLocalClassName() + "onRestart()->Restarted");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(CATEGORIA, getLocalClassName() + "onResume()->Resumed");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(CATEGORIA, getLocalClassName() + "onStart()->Started");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(CATEGORIA, getLocalClassName() + "onStop()->Stoped");
    }

    protected String getClassName(){
        return SourceActivity.class.getName();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ciclo_vida_log_cat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void voltar(View view){
        finish();
    }

}
