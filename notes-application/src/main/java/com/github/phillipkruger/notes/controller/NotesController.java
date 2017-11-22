package com.github.phillipkruger.notes.controller;

import com.github.phillipkruger.notes.Note;
import com.github.phillipkruger.notes.NoteExistAlreadyException;
import com.github.phillipkruger.notes.NoteNotFoundException;
import com.github.phillipkruger.notes.NotesService;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * JSF 2.2
 * Controller for notes
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@RequestScoped
@Named
public class NotesController {
    
    @EJB
    private NotesService notesService; 
    
    @Getter
    private List<Note> notes = null;
    
    @Getter @Setter
    private Note newNote = new Note();
    
    @PostConstruct
    public void refresh(){
        notes = notesService.getAllNotes();
        this.newNote = new Note();
    }
    
    public void addNote(){
        try {
            notesService.createNote(newNote.getTitle(), newNote.getText());
            refresh();
        } catch (NoteExistAlreadyException ex) {
            // TODO: To Screen
            log.log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateNote(String title,String text){
        try {
            Note note = notesService.getNote(title);
            note.setText(text);
            notesService.updateNote(note);
            refresh();
        } catch (NoteNotFoundException ex) {
            // TODO: To Screen
            log.log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteNote(String title){
        try {
            notesService.deleteNote(title);
            refresh();
        } catch (NoteNotFoundException ex) {
            // TODO: To Screen
            log.log(Level.SEVERE, null, ex);
        }
    }
}