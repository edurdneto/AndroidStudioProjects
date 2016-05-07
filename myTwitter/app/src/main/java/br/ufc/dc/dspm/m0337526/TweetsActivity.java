package br.ufc.dc.dspm.m0337526;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TweetsActivity extends Activity {
    ListView listTweets;
    int id=0;
    ArrayAdapter<Tweet> tA;
    private TweetDAO tweet;
    private static final int RESPOSTA = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);
        final TextView t=(TextView)findViewById(R.id.textView2);
        final Bundle b=getIntent().getExtras();
        tweet = new TweetDAO();
        t.post(new Runnable() {
            @Override
            public void run() {
                t.setText("@" + b.getString("id"));
            }
        });
        listTweets = (ListView) findViewById(R.id.listView);
        tA = new ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweet.list());
        listTweets.post(new Runnable() {
            @Override
            public void run() {
                listTweets.setAdapter(tA);
                listTweets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Tweet t = tweet.read(position);
                        Intent intent = new Intent();
                        intent.setAction("br.ufc.dc.dspm.action.tweets");
                        intent.setComponent(null);
                        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
                        intent.setComponent(null);
                        intent.putExtra("tweet", t.getText());
                        intent.putExtra("position", position);
                        startActivityForResult(intent, RESPOSTA);

                    }
                });
            }
        });


    }




    public void exemplo_layout(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(TweetsActivity.this);

        final EditText input = new EditText(TweetsActivity.this);
        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(140)});

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);

        //Cria o tweet ao ser clicado
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                id++;
                System.out.println(input.getText().toString());
                Tweet tw = new Tweet();
                tw.setId(id);
                tw.setText(input.getText().toString());
                tweet.add(tw);
                Toast.makeText(getApplicationContext(), "Tweet Criado com Sucesso", Toast.LENGTH_SHORT).show();
                tA.notifyDataSetChanged();

                if(tweet.list().size() %5 ==0){
                    showNotification();
                }
            }
        });

        //Cancela a operacao
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        builder.show();
    }

    public void sair(View view){

        finish();
    }

    private void showNotification() {

        NotificationManager manager;
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setTicker("tou morrendo");
        builder.setContentTitle("Explod");
        builder.setContentText("Now");
        builder.setSmallIcon(R.drawable.android_icon);
        Intent intent = new Intent(this, NotificationHanlder.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        manager.notify(R.string.app_name, notification);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESPOSTA) {
            if (resultCode == RESULT_OK) {
                int position = data.getIntExtra("pos", -1);
                String text = data.getStringExtra("tweet");
                Tweet tw = tweet.read(position);
                tw.setText(text);
                tA.notifyDataSetChanged();

            }
            if (resultCode == RESULT_CANCELED) {
                int position = data.getIntExtra("pos", -1);
                 tweet.remove(position);
                if(tweet.list().size() %5 ==0){
                    showNotification();
                }
                tA.notifyDataSetChanged();
            }

        }
    }

}
