package com.epam.java.se;

import javax.annotation.Nonnull;

/**
 * Stores a notes in format header + note
 *
 * @author Valeriy Burmistrov
 */
public class Note {

    /**
     * String of notes header
     */
    private String header;

    /**
     * String of notes text
     */
    private String noteText;

    /**
     * Create new Note with specified header and note
     *
     * @param header specified header of the Note
     * @param note specified text of the Note
     */
    public Note(@Nonnull String header, @Nonnull String note) {
        this.header = header;
        this.noteText = note;
    }

    public String getHeaderText() {
        return this.header;
    }

    public String getNoteText() {
        return this.noteText;
    }

    /**
     * concatenate header and text of the note to a general string
     *
     * @return result of concatenation
     */
    public String entireNoteToString() {
        return getHeaderText() + ": " + getNoteText();
    }
}