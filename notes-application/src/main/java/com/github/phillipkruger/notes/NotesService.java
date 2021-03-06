package com.github.phillipkruger.notes;

import com.github.phillipkruger.notes.event.Notify;
import com.github.phillipkruger.notes.stopwatch.Stopwatch;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.extern.java.Log;

/**
 * EJB 3.2, JPA 2.1, Interceptors 1.2, Bean validation 1.1
 * 
 * The actual implementation of the service.
 * Plain old Stateless service.
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Stateless
@Log
public class NotesService {
    
    //@Inject
    //private Cache<String,Note> cache;
    @PersistenceContext(name="com.github.phillipkruger.notes")
    private EntityManager em;
    
    @Notify("create") @Stopwatch
    public Note createNote(@NotNull @Size(min=2, max=20) String title,@NotNull String text) throws NoteExistAlreadyException{
        Note note = new Note(title, text);
        return createNote(note);
    }

    @Notify("create") @Stopwatch
    public Note createNote(@NotNull Note note) throws NoteExistAlreadyException{
        if(exist(note.getTitle()))throw new NoteExistAlreadyException("Could not create note [" + note.getTitle() + "]");
        note = em.merge(note);
        log.log(Level.INFO, "Created note [{0}]", note);
        return note;
    }
    
    public Note getNote(@NotNull @Size(min=2, max=20) String title) throws NoteNotFoundException{
        if(!exist(title))throw new NoteNotFoundException("Could not find note [" + title + "]");
        Note note = (Note)em.createNamedQuery(Note.QUERY_FIND_BY_TITLE)
					.setParameter("title", title)
					.getSingleResult();
        log.log(Level.INFO, "Retrieving note [{0}]", note);
        return note;
    }

    @Notify("delete") @Stopwatch
    public Note deleteNote(@NotNull @Size(min=2, max=20) String title) throws NoteNotFoundException{
        if(!exist(title))throw new NoteNotFoundException("Could not find note [" + title + "]");
        Note note = getNote(title);
        em.remove(note);
        log.log(Level.INFO, "Removing note [{0}]", title);
        return note;
    }

    @Notify("update") @Stopwatch
    public Note updateNote(@NotNull Note note) throws NoteNotFoundException{
        if(!exist(note.getTitle()))throw new NoteNotFoundException("Could not find note [" + note.getTitle() + "]");
        note = em.merge(note);
        log.log(Level.INFO, "Updated note [{0}]", note);
        return note;
    }

    public boolean exist(@NotNull @Size(min=2, max=20) String title){
        // Can this not be done with CriteriaBuilder ?
        Long count = (Long)em.createNamedQuery(Note.QUERY_COUNT_BY_TITLE)
					.setParameter("title", title)
					.getSingleResult();
        
        return count>0;
    }

    public List<String> getNoteTitles(){
        List<String> titles = new ArrayList<>();
        List<Note> notes = getAllNotes();
        if(notes!=null && !notes.isEmpty()){
            notes.forEach((note) -> {
                titles.add(note.getTitle());
            });
        }
        return titles;
    }
    
    public List<Note> getAllNotes() {
        return em.createNamedQuery("Note.findAll", Note.class).getResultList();
    }
}