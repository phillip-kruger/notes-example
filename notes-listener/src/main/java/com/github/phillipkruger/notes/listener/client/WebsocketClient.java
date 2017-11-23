package com.github.phillipkruger.notes.listener.client;

import java.io.IOException;
import java.net.URI;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Retry;

/**
 * Create a connection to the server
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Dependent
@Log
public class WebsocketClient {
    
    private Session session;
    
    @Inject
    @ConfigProperty(name = "notesUrl", defaultValue = "ws://localhost:8080/notes-application/note")
    private String notesUrl;
    
    @Retry(delay = 3,delayUnit = ChronoUnit.SECONDS, maxRetries = 28800) // Will retry for 24h
    public void connect() throws DeploymentException, IOException{
        if(isConnected()){
            log.log(Level.WARNING, "Already connected to [{0}]...", notesUrl);
        }else{
            log.log(Level.INFO, "Connecting to [{0}]...", notesUrl);
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(MessageClientEndpoint.class, URI.create(notesUrl));
        }
            
    }
    
    public void disconnect(){
        if(isConnected()){
            try {
                this.session.close();
                log.log(Level.WARNING, "Connection to [{0}] closed", notesUrl);
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Retry(delay = 3,delayUnit = ChronoUnit.SECONDS, maxRetries = 28800)
    public void reconnect() throws DeploymentException, IOException{
        disconnect();
        connect();
    }
    
    public boolean isConnected(){
        return session!=null && session.isOpen();
    }
}
