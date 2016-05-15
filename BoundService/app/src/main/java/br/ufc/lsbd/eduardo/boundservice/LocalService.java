package br.ufc.lsbd.eduardo.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

/**
 * Created by eduardo on 2016-05-10.
 */
public class LocalService extends Service {
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder{
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int sum(int i, int j){
        return i+j;
    }

    public int sub(int i, int j){
        return i-j;
    }

    public int mult(int i, int j){
        return i*j;
    }

    public int div(int i, int j){
        return i/j;
    }
}
