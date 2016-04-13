package com.example.eduardo.ciclovidalogcat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.*;

public class SourceActivity extends Activity {

    private static final String CATEGORIA = "CicloVida";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.source_layout);
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


    public void startActivityExplicita(View view){
        Intent intent = new Intent (SourceActivity.this, TargetActivity.class);
        startActivity(intent);
    }

    public void startActivityImplicita(View view){
        Intent intent = new Intent ();
        intent.setAction("br.ufc.dc.dspm.action.Acao");
        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
    }

}
