package com.github.phillipkruger.notes.listener;

import com.github.phillipkruger.notes.listener.client.MessageStorage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import lombok.extern.java.Log;

/**
 * Basic API
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Path("")
public class ListenerApi {
    
    @Inject
    private MessageStorage messageStorage;
    
    @GET
    @Path("/peek")
    public String peek(){
        return messageStorage.getMessagesStack().peek();
    }
    
    @GET
    @Path("/list")
    public String list() throws IOException{
        
        try(StringWriter stringWriter = new StringWriter()){
            Enumeration<String> elements = messageStorage.getMessagesStack().elements();
            while (elements.hasMoreElements()) {
                String nextElement = elements.nextElement();
                stringWriter.write(nextElement);
                stringWriter.write("\n");
            }
            return stringWriter.toString();
        }   
    }
    
}