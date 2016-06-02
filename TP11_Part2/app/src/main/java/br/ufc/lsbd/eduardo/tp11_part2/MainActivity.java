package br.ufc.lsbd.eduardo.tp11_part2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    public void temperature (View view){
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(getApplicationContext(), "Nao possui o sensor", Toast.LENGTH_SHORT).show();
        }

    }

    public void luminosidade (View view){
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(getApplicationContext(), "Nao possui o sensor", Toast.LENGTH_SHORT).show();
        }

    }

    public void umidade (View view){
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null){
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(getApplicationContext(), "Nao possui o sensor", Toast.LENGTH_SHORT).show();
        }

    }

    public void pressao (View view){
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null){
            sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(getApplicationContext(), "Nao possui o sensor", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final float even=event.values[0];
        final TextView t=(TextView)findViewById(R.id.textView);
        t.post(new Runnable() {
            @Override
            public void run() {
                t.setText(Float.toString(even));
            }
        });
        //mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
