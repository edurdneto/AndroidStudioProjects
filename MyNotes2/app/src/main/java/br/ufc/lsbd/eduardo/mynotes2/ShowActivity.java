package br.ufc.lsbd.eduardo.mynotes2;

import android.content.Intent;
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

public class ShowActivity extends AppCompatActivity {
    private NoteDAO noteDAO;
    ArrayList<Note> notes=new ArrayList<>();
    ArrayAdapter<Note> tA;
    ListView listNotes;
    int RESPOSTA=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        noteDAO = new NoteDAO(this);
        final List<Note> notes = noteDAO.list();
        StringBuffer buffer = new StringBuffer();
        listNotes=(ListView)findViewById(R.id.listView2);
        tA = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, noteDAO.list());
        listNotes.post(new Runnable() {
            @Override
            public void run() {
                listNotes.setAdapter(tA);
                listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int i=0;
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
                        intent.putExtra("position", n.getId());
                        intent.putExtra("title", n.getTitle());
                        intent.putExtra("content", n.getContent());
                        intent.putExtra("data", n.getData());
                        startActivityForResult(intent, RESPOSTA);
                    }
                });
            }
        });
    }

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
    }

    public void sair(View view){
        finish();
    }
}
