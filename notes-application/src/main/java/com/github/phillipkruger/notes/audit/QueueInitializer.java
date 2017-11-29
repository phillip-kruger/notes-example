package com.github.phillipkruger.notes.audit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.jms.JMSContext;
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
    name="java:app/NotesQueue",
    interfaceName="javax.jms.Queue",
    destinationName="notesQueue")
@Startup
@Singleton
public class QueueInitializer {
        
    @Resource(lookup = "java:app/NotesQueue")
    private Queue queue;
    
    @Inject
    private JMSContext jmsContext;
    
    @Produces @NotesQueue
    public Queue exposeQueue() {
        return this.queue;
    }
    
    @Produces @NotesQueue
    public JMSContext exposeContext(){
        return this.jmsContext;
    }
    
    @PostConstruct
    public void init(){
        log.info("Notes application: Queue initialized [ok]");
    }
}
