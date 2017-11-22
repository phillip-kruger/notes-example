package com.github.phillipkruger.notes.audit;

import com.github.phillipkruger.notes.Note;
import com.github.phillipkruger.notes.event.ChangeEvent;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 * CDI 1.1, JMS 2.0.
 * Create a Audit message
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Stateless
public class AuditProducer {

    @Inject
    @JMSConnectionFactory("jms/notesQueueCF") // Not needed if not OpenLiberty
    private JMSContext context;
    
    @Inject
    private NotesMarshaller marshaller;
    
    @Inject @NotesQueue
    private Queue queue;
    
    public void receiveMovement(@Observes ChangeEvent changeEvent){
        if(queue!=null){
            Note note = changeEvent.getNote();
            byte[] message = marshaller.marshall(note);
        
            JMSProducer producer = context.createProducer();
            producer.setProperty(ACTION_PROPERTY, changeEvent.getType());
            producer.send(queue, message);
        }
    }
    
    private static final String ACTION_PROPERTY = "ChangeEvent";
}
