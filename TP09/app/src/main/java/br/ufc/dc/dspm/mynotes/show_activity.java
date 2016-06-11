package br.ufc.dc.dspm.mynotes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class show_activity extends Activity {

    //private NoteDAO noteDAO;
    ArrayList<Note> notes=new ArrayList<>();
    ArrayAdapter<Note> tA;
    ListView listNotes;
    int RESPOSTA=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //noteDAO = new NoteDAO(this);
        final List<Note> notes = new ArrayList<Note>();
        StringBuffer buffer = new StringBuffer();
        String URL = NotesProvider.URL;
        Uri notesURI = Uri.parse(URL);
        Cursor cursor = getContentResolver().query(notesURI, null, null, null, NotesProvider.ID);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(NotesProvider.ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(NotesProvider.TITLE)));
                note.setContent(cursor.getString(cursor.getColumnIndex(NotesProvider.CONTENT)));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        listNotes=(ListView)findViewById(R.id.listView2);
        tA = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, notes);
        listNotes.post(new Runnable() {
            @Override
            public void run() {
                listNotes.setAdapter(tA);
                listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        /*int i=0;
                        Iterator<Note> it = notes.iterator();
                        System.out.println("position="+position);
                        System.out.println("i="+i);
                        Note n=new Note();
                        while(i<=position) {
                            n = it.next();
                            i++;
                        }
                        Intent intent = new Intent();
                        intent.setAction("br.ufc.dc.dspm.action.editnote");
                        intent.setComponent(null);
                        intent.addCategory("br.ufc.dc.dspm.category.Categoria");
                        intent.setComponent(null);
                        //intent.putExtra("position", n.getId());
                        //intent.putExtra("title", n.getTitle());
                        //intent.putExtra("content", n.getContent());
                        //intent.putExtra("data", n.getData());
                        startActivityForResult(intent, RESPOSTA);
                    */}
                });
            }
        });
    }
/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESPOSTA) {
            if (resultCode == RESULT_OK) {
                Note note=new Note();
                note.setId(data.getIntExtra("position",-1));
                note.setTitle(data.getStringExtra("title"));
                note.setContent(data.getStringExtra("content"));
                note.setData(data.getStringExtra("data"));
                noteDAO.update(note);
                tA.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Note Editada", Toast.LENGTH_SHORT).show();
                finish();

            }
            if (resultCode == RESULT_CANCELED) {
                noteDAO.delete(data.getIntExtra("position",-1));
                Toast.makeText(getApplicationContext(), "Note Apagada", Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }*/

    public void sair(View view){
        finish();
    }

}
