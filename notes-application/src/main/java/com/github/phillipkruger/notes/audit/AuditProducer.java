package com.github.phillipkruger.notes.audit;

import com.github.phillipkruger.notes.Note;
import com.github.phillipkruger.notes.event.ChangeEvent;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import lombok.extern.java.Log;

/**
 * CDI 1.1, JMS 2.0.
 * Create a Audit message
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Stateless
public class AuditProducer {

    @Inject @NotesQueue
    private JMSContext context;
    
    @Inject @NotesQueue
    private Queue queue;
    
    @Inject
    private NotesMarshaller marshaller;
    
    public void receiveMovement(@Observes ChangeEvent changeEvent){
        Note note = changeEvent.getNote();
        byte[] message = marshaller.marshall(note);

        JMSProducer producer = context.createProducer();
        producer.setProperty(ACTION_PROPERTY, changeEvent.getType());
        producer.send(queue, message);
        log.info("Added changeEvent to audit queue");
    }
    
    private static final String ACTION_PROPERTY = "ChangeEvent";
}
