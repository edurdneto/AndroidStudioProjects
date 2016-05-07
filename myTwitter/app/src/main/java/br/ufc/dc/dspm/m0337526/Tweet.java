package br.ufc.dc.dspm.m0337526;

/**
 * Created by eduardo on 2016-05-03.
 */
public class Tweet {
    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
