package com.github.phillipkruger.notes.listener.client;

import java.util.Stack;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import lombok.Getter;
import lombok.extern.java.Log;

/**
 * Store all message
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@ApplicationScoped
@Log
public class MessageStorage {
    
    @Getter
    private final Stack<String> messagesStack = new Stack<>();
    
    public void store(@Observes String message){
        messagesStack.push(message);
        log.info("================ NEW MESSAGE ===================\n"
                + messagesStack.peek());
    }
    
    
    
}
