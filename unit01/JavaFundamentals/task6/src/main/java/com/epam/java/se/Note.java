package com.epam.java.se;

import javax.annotation.Nonnull;

/**
 * Created by chris on 20.02.2017.
 */
public class Note {

    private String header;

    private String note;

    public Note(@Nonnull String header, @Nonnull String note) {
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