package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chris on 23.02.2017.
 */
public class NotebookTest {
    @Test
    public void testAddNoteToNotebookByHeaderAndNote() throws Exception {
        String testHeader = "test header";
        String testNote = "test note";
        Notebook notebook = new Notebook();
        notebook.addNote(testHeader, testNote);
    }

    @Test
    public void testAddNoteToNotebookByNoteNewNote() throws Exception {
        String testHeader = "test header";
        String testNote = "test note";
        Notebook notebook = new Notebook();
        notebook.addNote(new Note(testHeader, testNote));
    }

    @Test
    public void testNoteAddedByHeaderAndNoteIsContainedInNotebook() throws Exception {
        String testHeader = "test header";
        String testNote = "test note";
        Notebook notebook = new Notebook();
        notebook.addNote(testHeader, testNote);
        assertEquals(notebook.getNote(0).entireNoteToString(), testHeader + " " + testNote);
    }

    @Test
    public void testNoteAddedByNewNoteIsContainedInNotebook() throws Exception {
        String testHeader = "test header";
        String testNote = "test note";
        Notebook notebook = new Notebook();
        notebook.addNote(new Note(testHeader, testNote));
        assertEquals(notebook.getNote(0).entireNoteToString(), testHeader + " " + testNote);
    }

    @Test
    public void removeNote() throws Exception {

    }

}