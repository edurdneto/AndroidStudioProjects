package br.ufc.lsbd.eduardo.tp11;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.List;

public class MainActivity extends Activity {
    TextView tv1=null;
    private SensorManager mSensorManager;
    ListView listSensor;
    ArrayAdapter<Sensor> tA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView2);
        tv1.setVisibility(View.GONE);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mList= mSensorManager.getSensorList(Sensor.TYPE_ALL);

        /*for (int i = 1; i < mList.size(); i++) {
            tv1.setVisibility(View.VISIBLE);
            tv1.append("\n" + mList.get(i).getName() + "\n" + mList.get(i).getVendor() + "\n" + mList.get(i).getVersion());
        }*/
        listSensor=(ListView)findViewById(R.id.listView);
        tA = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList);
        listSensor.post(new Runnable() {
            @Override
            public void run() {
                listSensor.setAdapter(tA);
                listSensor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     /*   Tweet t = tweet.read(position);
                        Intent intent = new Intent();
                        intent.setAction("br.ufc.dc.dspm.action.tweets");
                        intent.setComponent(null);
                        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
                        intent.setComponent(null);
                        intent.putExtra("tweet", t.getText());
                        intent.putExtra("position", position);
                        startActivityForResult(intent, RESPOSTA);
                    */
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
