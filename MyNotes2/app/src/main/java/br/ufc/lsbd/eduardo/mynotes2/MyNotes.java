package br.ufc.lsbd.eduardo.mynotes2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyNotes extends Activity {

        private NoteDAO noteDAO;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my_notes);
            noteDAO = new NoteDAO(this);
        }

        @Override
        protected void onResume(){
            super.onResume();
            EditText title=(EditText)findViewById(R.id.editTextTitle);
            EditText content=(EditText)findViewById(R.id.editTextContent);
            EditText data=(EditText)findViewById(R.id.editTextData);
            title.setText("");
            content.setText("");
            data.setText("");
            title.setHint("note title");
            content.setHint("note content");
            data.setHint("Data");
        }

        public void addNote(View view) {
            EditText titleText = (EditText) findViewById(R.id.editTextTitle);
            EditText contentText = (EditText) findViewById(R.id.editTextContent);
            EditText dateText = (EditText) findViewById(R.id.editTextData);
            Note note = new Note();
            note.setTitle(titleText.getText().toString());
            note.setContent(contentText.getText().toString());
            note.setData(dateText.getText().toString());
            noteDAO.create(note);
            Toast.makeText(getApplicationContext(), "Note Criada", Toast.LENGTH_SHORT).show();
        }

        public void show(View view){
            List<Note> notes = noteDAO.list();
            if(notes != null) {
                Intent i = new Intent();
                i.setAction("br.ufc.dc.dspm.action.show");
                i.setComponent(null);
                i.addCategory("br.ufc.dc.dspm.category.Categoria");
                i.setComponent(null);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "0 Notes", Toast.LENGTH_SHORT).show();
            }
        }

}
