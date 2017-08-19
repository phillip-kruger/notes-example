package com.github.phillipkruger.notes.audit;

import com.github.phillipkruger.notes.Note;
import com.github.phillipkruger.notes.event.ChangeEvent;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
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
@JMSDestinationDefinition(interfaceName = "javax.jms.Queue",
                          name = "java:app/jms/auditQueue",
                          destinationName = "auditQueue")
public class AuditProducer {

    @Inject
    private JMSContext context;
    
    @Inject
    private NotesMarshaller marshaller;
    
    @Resource(mappedName = "java:app/jms/auditQueue")
    private Queue queue;
    
    public void receiveMovement(@Observes ChangeEvent changeEvent){
        Note note = changeEvent.getNote();
        byte[] message = marshaller.marshall(note);
        
        JMSProducer producer = context.createProducer();
        producer.setProperty("ChangeEvent", changeEvent.getType());
        producer.send(queue, message);
    }
}
