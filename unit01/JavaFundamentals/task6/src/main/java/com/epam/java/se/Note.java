package com.epam.java.se;

/**
 * Created by chris on 20.02.2017.
 */
public class Note {

    private String header;

    private String note;

    public Note(String header,String note) {
        this.header = header;
        this.note = note;
    }

    public String getHeaderText() {
        return this.header;
    }

    public String getNoteText() {
        return this.note;
    }

    public String entireNoteToString() {
        return getHeaderText() + " " + getNoteText();
    }
}