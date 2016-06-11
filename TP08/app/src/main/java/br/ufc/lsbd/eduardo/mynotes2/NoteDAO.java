package br.ufc.lsbd.eduardo.mynotes2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;
/**
 * Created by eduardo on 2016-05-12.
 */
public class NoteDAO extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyNotes.db";
    public static final int DATABASE_VERSION = 1;

    public NoteDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public NoteDAO(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sql = new StringBuffer();
        sql.append("create table notes (");
        sql.append("id integer primary key autoincrement,");
        sql.append("title text,");
        sql.append("content text,");
        sql.append("data text)");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists notes");
        onCreate(db);
    }

    public void create(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("content", note.getContent());
        contentValues.put("data", note.getData());
        long id = db.insert("notes", null, contentValues);
        Log.v("SQLite", "create id = " + id);
    }

    public Note retrieve(Integer id) {
        String[] fieldValues = new String[1];
        fieldValues[0] = Integer.toString(id+1);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from notes where id = ?", fieldValues);
        Note note = null;
        result.moveToFirst();
        if (result != null && result.getCount() > 0) {
            note = new Note();
            note.setId(result.getInt(0));
            note.setTitle(result.getString(1));
            note.setContent(result.getString(2));
            note.setData(result.getString(3));
        }
        return note;
    }

    public void update(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("content", note.getContent());
        contentValues.put("data",note.getData());
        String[] fieldValues = new String[1];
        fieldValues[0] = Integer.toString(note.getId());
        db.update("notes", contentValues, " id = ? ", fieldValues);
    }

    public void delete(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("notes", " id = ? ", new String[]{Integer.toString(id)});
        //db.execSQL("drop table if exists notes");
    }

    public List<Note> list() {
        List<Note> notes = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from notes order by id", null);
        if (result != null && result.getCount() > 0) {
            notes = new ArrayList<Note>();
            result.moveToFirst();
            while (result.isAfterLast() == false) {
                Note note = new Note();
                note.setId(result.getInt(0));
                note.setTitle(result.getString(1));
                note.setContent(result.getString(2));
                note.setData(result.getString(3));
                notes.add(note);
                result.moveToNext();
            }
        }
        return notes;
    }

}
