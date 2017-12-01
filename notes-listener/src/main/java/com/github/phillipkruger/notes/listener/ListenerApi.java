package com.github.phillipkruger.notes.listener;

import com.github.phillipkruger.notes.listener.client.MessageStorage;
import com.github.phillipkruger.notes.listener.client.WebsocketClient;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;
import javax.inject.Inject;
import javax.websocket.DeploymentException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
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
    
    @Inject 
    private WebsocketClient websocketClient;
    
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
            }
            return stringWriter.toString();
        }   
    }
    
    @GET
    @Path("/reconnect")
    public String reconnect() throws DeploymentException, IOException{
        websocketClient.disconnect();
        return list();
    }
    
    @GET
    @Path("/isconnected")
    public Response isConnected() throws DeploymentException, IOException{
        boolean ic = websocketClient.isConnected();
        return Response.ok(String.valueOf(ic)).build();
    }
    
}