package br.ufc.lsbd.eduardo.webdownloader;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ConnectException;

public class MainActivity extends Activity {
    private ConnectivityManager connManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void download (View view){
        TextView t = (TextView)findViewById(R.id.textView);
        DownloaderTask a = new DownloaderTask(t);

        if(checkConnection(view)) {
            String ed = ((EditText) findViewById(R.id.editText)).getText().toString();
            a.execute(ed);
        }
    }

    public boolean checkConnection( View view) {
        connManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = info.isConnected();
        if (isWifiConn) {
            return isWifiConn;
        }
        //Toast.makeText(this, "Is Wi-Fi connected? " + isWifiConn, Toast.LENGTH_SHORT).show();
        info = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = info.isConnected();
        //Toast.makeText(this, "Is Mobile connected? " + isMobileConn, Toast.LENGTH_SHORT).show();
        if (isMobileConn) {
            return isMobileConn;
        } else {
            Toast.makeText(this, "Is Mobile connected? " + isMobileConn, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
