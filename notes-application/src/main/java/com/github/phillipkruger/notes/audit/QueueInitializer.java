package com.github.phillipkruger.notes.audit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Queue;
import lombok.extern.java.Log;

/**
 * CDI 1.1, JMS 2.0.
 * Create the queue and expose as CDI resource
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@JMSDestinationDefinition(
    name="java:app/notesQueue",
    interfaceName="javax.jms.Queue",
    destinationName="notesQueue")
@Startup
@Singleton
public class QueueInitializer {
    
    @Produces @NotesQueue
    public Queue exposeQueue() {
        return this.queue;
    }
    
    @Resource(lookup = "java:app/notesQueue")
    private Queue queue;
    
    @PostConstruct
    public void init(){
        log.info("Notes application: Queue initialized [ok]");
    }
}
