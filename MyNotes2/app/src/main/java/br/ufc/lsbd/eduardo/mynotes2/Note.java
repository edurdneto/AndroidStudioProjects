package br.ufc.lsbd.eduardo.mynotes2;

import java.util.Date;

/**
 * Created by eduardo on 2016-05-12.
 */
public class Note {
    private int id;

    private String title;

    private String content;

    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "(" + id + ") " + title + ": " + content +" , "+ data;
    }
}
