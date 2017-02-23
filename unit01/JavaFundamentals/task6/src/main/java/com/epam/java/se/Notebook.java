package com.epam.java.se;


import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * Stores an array of the notes
 *
 * @author Valeriy Burmistrov
 */
public class Notebook {

    /**
     * array of the storing notes
     */
    private Note[] notes;

    /**
     * amount of storing notes
     */
    private int size;

    /**
     * create a new default Notebook
     */
    public Notebook() {
        notes = new Note[1];
        size = 0;
    }

    /**
     * adds new Note to Notebook
     *
     * @param note a Note needed to be added
     */
    public void addNote(@Nonnull Note note) {
        ensureCapacity(size + 1);
        notes[size] = note;
        size += 1;
    }

    /**
     * constructs a new Note, based on specified header and noteText, and adds this Note to Notebook
     *
     * @param header specified header of new Note
     * @param noteText specified text of new Note
     */
    public void addNote(@Nonnull String header, @Nonnull String noteText) {
        Note noteToAdd = new Note(header, noteText);
        this.addNote(noteToAdd);
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= getCapacity()) {
            return;
        }
        final int newCapacity = Math.max(requiredCapacity, (getCapacity() * 3) / 2 + 1);
        notes = Arrays.copyOf(notes, newCapacity);
    }

    private int getCapacity() {
        return notes.length;
    }

    /**
     * removes Note at the specified index from Notebook
     *
     * @param index index of the Note needed to be removed
     */
    public void removeNote(int index) {
        if (size == 0) {
            return;
        }
        for (int i = index; i < getIndexOfTheLastElementSize(); i++) {
            this.notes[i] = this.notes[i + 1];
        }
        this.notes[getIndexOfTheLastElementSize()] = null;
        size -= 1;
    }

    /**
     * gets the Note at the specified index
     *
     * @param index index of the Noted needed to get
     * @return the Note at the specified index
     */
    public Note getNote(int index) {
        return this.notes[index];
    }

    private int getIndexOfTheLastElementSize() {
        return size - 1;
    }

    /**
     * creates new Note based on new header and new noteText,
     * add this Note in Notebook at the specified index instead of existing one
     *
     * @param index index of the Note needed to edit
     * @param headerCorrection new header
     * @param noteTextCorrection new noteText
     */
    public void editNote(int index, @Nonnull String headerCorrection, @Nonnull String noteTextCorrection) {
        if (index < 0 || index >= size) {
            return;
        }
        this.notes[index] = new Note(headerCorrection, noteTextCorrection);
    }

    /**
     * prints content of every Note in Notebook
     */
    public void showAllNotes() {
        for (int i = 0; i < size; i++) {
            System.out.println(notes[i].entireNoteToString());
        }
    }
}
