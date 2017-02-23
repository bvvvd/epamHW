package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chris on 23.02.2017.
 */
public class NotebookTest {

    @Test
    public void testAddNoteToNotebookByNullHeaderAndNullNoteThrowsIllegalArgumentException() throws Exception {
        Notebook notebook = new Notebook();
        try {
            notebook.addNote(new Note(null, null));
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testAddNoteToNotebookByHeaderAndNote() throws Exception {
        String testHeader = "test header";
        String testNote = "test note";
        Notebook notebook = new Notebook();
        notebook.addNote(testHeader, testNote);
    }

    @Test
    public void testAddNullNoteToNotebookThrowsIllegalArgumentException() throws Exception {
        Notebook notebook = new Notebook();
        try {
            notebook.addNote(null);
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testAddNoteToNotebookByNoteNewNote() throws Exception {
        String testHeader = "test header";
        String testNote = "test note";
        Notebook notebook = new Notebook();
        notebook.addNote(new Note(testHeader, testNote));
    }

    @Test
    public void testNoteAddedByHeaderAndNoteWhichIsContainedInNotebook() throws Exception {
        String testHeader = "test header";
        String testNote = "test note";
        Notebook notebook = new Notebook();
        notebook.addNote(testHeader, testNote);
        assertEquals(notebook.getNote(0).entireNoteToString(), testHeader + ": " + testNote);
    }

    @Test
    public void testNoteAddedByNewNoteWhichIsContainedInNotebook() throws Exception {
        String testHeader = "test header";
        String testNote = "test note";
        Notebook notebook = new Notebook();
        notebook.addNote(new Note(testHeader, testNote));
        assertEquals(notebook.getNote(0).entireNoteToString(), testHeader + ": " + testNote);
    }

    @Test
    public void testRemoveAddedNote() throws Exception {
        Notebook notebook = new Notebook();
        notebook.addNote("testHeader", " testNote");
        notebook.addNote("testHeader1", " testNote1");
        notebook.removeNote(0);
        assertEquals(notebook.getNote(0).entireNoteToString(), "testHeader1:  testNote1");
        notebook.removeNote(0);
        assertEquals(notebook.getNote(0), null);
    }

    @Test
    public void testTryingRemoveNoteFromEmptyNotebookDoesNothing() throws Exception {
        Notebook notebook = new Notebook();
        notebook.removeNote(0);
    }

    @Test
    public void testRemoveNoteWithIndexMoreThanExistingDoesNothing() throws Exception {
        Notebook notebook = new Notebook();
        notebook.removeNote(1);
        notebook.removeNote(15);
    }

    @Test
    public void testEditExistingNote() throws Exception {
        Notebook notebook = new Notebook();
        notebook.addNote("testHeader", "testNote");
        notebook.editNote(0, "testHeaderEdited", "testNoteEdited");
        assertEquals(notebook.getNote(0).entireNoteToString(), "testHeaderEdited: testNoteEdited");
    }

    @Test
    public void testEditNotExistingNoteDoesNothing() throws Exception {
        Notebook notebook = new Notebook();
        notebook.editNote(15, "testHeaderEdited", "testNoteEdited");
        notebook.editNote(0, "testHeaderEdited", "testNoteEdited");
        assertEquals(notebook.getNote(0), null);
    }

    @Test
    public void testEditNoteWithNullArgumentsThrowsIllegalArgumentException() {
        Notebook notebook = new Notebook();
        try {
            notebook.editNote(0, null, null);
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testShowAllNotes() throws Exception {
        Notebook notebook = new Notebook();
        notebook.addNote("firstHeader", "firstNote");
        notebook.addNote("secondHeader", "secondNote");
        notebook.addNote("thirdHeader", "thirdNote");
        notebook.showAllNotes();
    }
}