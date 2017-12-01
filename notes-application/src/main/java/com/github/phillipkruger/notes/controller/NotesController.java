package com.github.phillipkruger.notes.controller;

import com.github.phillipkruger.notes.Note;
import com.github.phillipkruger.notes.NoteExistAlreadyException;
import com.github.phillipkruger.notes.NoteNotFoundException;
import com.github.phillipkruger.notes.NoteStyle;
import com.github.phillipkruger.notes.NotesService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
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
public class NotesController implements Serializable {
    
    @EJB
    private NotesService notesService; 
    
    @Getter
    private List<Note> notes = null;
    
    @Getter @Setter
    private Note newNote = new Note();
    
    @Getter
    private final NoteStyle[] noteStyles = NoteStyle.values(); 
    
    @PostConstruct
    public void refresh(){
        notes = notesService.getAllNotes();
        this.newNote = new Note();
    }
    
    public void addNote(){
        try {
            notesService.createNote(newNote);
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
    
    public String getCssClass(NoteStyle noteStyle){
        if(noteStyle == NoteStyle.yellow) return "panel panel-warning";
        if(noteStyle == NoteStyle.green) return "panel panel-success";
        if(noteStyle == NoteStyle.white) return "panel panel-default";
        if(noteStyle == NoteStyle.blue) return "panel panel-info";
        if(noteStyle == NoteStyle.red) return "panel panel-danger";
        
        return null;
    }
}