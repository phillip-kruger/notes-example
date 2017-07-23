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

/**
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Notify
@Interceptor
@Log
public class NotifyInterceptor implements Serializable {

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        Note note = (Note)ic.proceed();
        
        Method method = ic.getMethod();
        String name = method.getName();
        Notify annotation = method.getAnnotation(Notify.class);
        
        ChangeEvent changeEvent = new ChangeEvent(note, annotation.value());
        log.log(Level.INFO, "Intercepting [{0}] : {1}", new Object[]{name, changeEvent});
        broadcaster.fire(changeEvent);
        
        return note;
    }
    
    @Inject
    private Event<ChangeEvent> broadcaster;
}
