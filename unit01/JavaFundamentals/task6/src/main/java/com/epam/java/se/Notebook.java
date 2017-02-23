package com.epam.java.se;


import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * Created by chris on 23.02.2017.
 */
public class Notebook {

    private Note[] notes;

    private int size;

    public Notebook() {
        notes = new Note[1];
        size = 0;
    }

    public void addNote(@Nonnull Note note) {
        ensureCapacity(size + 1);
        notes[size] = note;
        size += 1;
    }

    public void addNote(@Nonnull String header, @Nonnull String note) {
        Note noteToAdd = new Note(header, note);
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

    public Note getNote(int index) {
        return this.notes[index];
    }

    private int getIndexOfTheLastElementSize() {
        return size - 1;
    }

    public void editNote(int index, @Nonnull String headerCorrection, @Nonnull String noteCorrection) {
        if (index < 0 || index >= size) {
            return;
        }
        this.notes[index] = new Note(headerCorrection, noteCorrection);
    }
}
