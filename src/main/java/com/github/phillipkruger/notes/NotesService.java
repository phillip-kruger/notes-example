package com.github.phillipkruger.notes;

import com.github.phillipkruger.notes.event.EventType;
import com.github.phillipkruger.notes.event.Notify;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
//import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.extern.java.Log;

@Stateless
@Log
//@DeclareRoles({"owner"})
public class NotesService {
    
    //@Inject
    //private Cache<String,Note> cache;
    private static Map<String,Note> cache = new HashMap<>();
    
    @Notify(type = EventType.CREATE)
    //TODO: RolesAllowed("owner")
    public Note createNote(
            @NotNull @Size(min=2, max=20)
            String title,@NotNull String text) throws NoteExistAlreadyException{
        
        if(exist(title))throw new NoteExistAlreadyException();
        Note note = new Note(title, text);
        save(note);
        log.log(Level.INFO, "Created note [{0}]", note);
        return note;
    }

    public Note getNote(
            @NotNull @Size(min=2, max=20)
            String title) throws NoteNotFoundException{
        
        if(!exist(title))throw new NoteNotFoundException();
        Note note = cache.get(title);
        log.log(Level.INFO, "Retrieving note [{0}]", note);
        return note;
    }

    @Notify(type = EventType.DELETE)
    public Note deleteNote(
            @NotNull @Size(min=2, max=20)
            String title) throws NoteNotFoundException{
        if(!exist(title))throw new NoteNotFoundException();
        Note note = cache.get(title);
        cache.remove(title);
        log.log(Level.INFO, "Removing note [{0}]", title);
        return note;
    }

    @Notify(type = EventType.UPDATE)
    public Note updateNote(@NotNull Note note) throws NoteNotFoundException{
        
        if(!exist(note.getTitle()))throw new NoteNotFoundException();
        save(note);
        log.log(Level.INFO, "Updated note [{0}]", note);
        return note;
    }

    public boolean exist(
            @NotNull @Size(min=2, max=20)
                    String title){
        
        return cache.containsKey(title);
    }

    public List<String> getNoteTitles(){
        return new ArrayList<>(cache.keySet());
//        List<String> titles = new ArrayList<>();
//        Iterator<Cache.Entry<String, Note>> iterator = cache.iterator();
//        while (iterator.hasNext()) {
//            Cache.Entry<String, Note> next = iterator.next();
//            titles.add(next.getKey());
//        }
//        return titles;
    }
    
    private void save(Note note){
        cache.put(note.getTitle(), note);
    }
    
}