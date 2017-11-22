package com.github.phillipkruger.notes.listener.client;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.servlet.ServletContext;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import lombok.extern.java.Log;

/**
 * On startup create a connection to the server.
 * TODO: What is the server is not available ?
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@ApplicationScoped
@Log
public class MessageClientListener {
    
    public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext init) {
        
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080/notes-application/note";
        
        try {
            container.connectToServer(MessageClientEndpoint.class, URI.create(uri));
        } catch (DeploymentException | IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        log.info("========= Notes client started =========");
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) ServletContext init) {
        log.info("========= Notes client died =========");
    }
}