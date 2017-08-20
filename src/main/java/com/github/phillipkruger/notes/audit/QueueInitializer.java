package com.github.phillipkruger.notes.audit;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Queue;

/**
 * CDI 1.1, JMS 2.0.
 * Create the queue and expose as CDI resource
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@JMSDestinationDefinitions(
        value = {
            @JMSDestinationDefinition(
                    name = "java:global/jms/notesQueue",
                    interfaceName = "javax.jms.Queue",
                    destinationName = "notesQueue"
            )
        }
)
@Startup
@Singleton
public class QueueInitializer {

    @Resource(mappedName="java:global/jms/notesQueue")
    private Queue queue;      
    
    @Produces @NotesQueue
    public Queue expose() {
        return this.queue;
    }
}
