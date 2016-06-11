package br.ufc.lsbd.eduardo.webdownloader;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eduardo on 2016-05-24.
 */
public class DownloaderTask extends AsyncTask<String, String, String> {
    private TextView textView;
    public DownloaderTask(TextView textView) {
        this.textView = textView;
    }

    protected String doInBackground(String... params) {
        try {
            String link = (String) params[0];
            //connection
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            //read content
            InputStream is = conn.getInputStream();
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8")); String data = null;
            String content = "";
            while ((data = reader.readLine()) != null) {
                content += data + "\n";
            }
            return content;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }
    protected void onProgressUpdate(String result) {

    }

    protected void onPostExecute(String result) {
        this.textView.setText(result);
    }
}
