package com.github.phillipkruger.notes.audit;

import java.util.logging.Level;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;

import javax.jms.Message;
import javax.jms.MessageListener;
import lombok.extern.java.Log;

/**
 * JMS 2.0.
 * Receive a Audit message
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@MessageDriven(name="notesQueueMDB", mappedName="java:app/notesQueue",activationConfig =  {
    @ActivationConfigProperty(propertyName = "destinationType",
                              propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination",
            propertyValue = "java:app/notesQueue")
})
public class AuditConsumer implements MessageListener {
    
    @Override
    public void onMessage(Message message) {
        try {
            String action = message.getStringProperty(ACTION_PROPERTY);
            byte[] o = message.getBody(byte[].class);
            print(action,new String(o));
        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void print(String action,String object){
        log.log(Level.SEVERE, "(JMS) AUDIT: {0}| {1}", new Object[]{action, object});
    }
    
    private static final String ACTION_PROPERTY = "ChangeEvent";
}
