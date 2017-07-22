package com.github.phillipkruger.notes.event;

import com.github.phillipkruger.notes.Note;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.logging.Level;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import lombok.extern.java.Log;

@Notify
@Interceptor
@Log
public class NotifyInterceptor implements Serializable {

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        Note note = (Note)ic.proceed();
        
        Method method = ic.getMethod();
        Notify annotation = method.getAnnotation(Notify.class);
        
        switch (annotation.type()) {
            case CREATE:
                notifyCreate(note);
                break;
            case UPDATE:
                notifyUpdate(note);
                break;
            case DELETE:
                notifyDelete(note);
                break;
            default:
                break;
        }
        return note;
    }
    
    private void notifyCreate(Note note){
        log.log(Level.INFO, "Interceptor: Create {0}", note);
        broadcaster.fire(new ChangeEvent(note, EventType.CREATE));
    }
    
    private void notifyUpdate(Note note){
        log.log(Level.INFO, "Interceptor: Update {0}", note);
        broadcaster.fire(new ChangeEvent(note, EventType.UPDATE));
    }
    
    private void notifyDelete(Note note){
        log.log(Level.INFO, "Interceptor: Delete {0}", note);
        broadcaster.fire(new ChangeEvent(note, EventType.DELETE));
    }
    
    @Inject
    private Event<ChangeEvent> broadcaster;
}
