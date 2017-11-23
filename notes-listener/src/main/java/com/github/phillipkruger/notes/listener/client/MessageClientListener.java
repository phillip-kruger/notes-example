package com.github.phillipkruger.notes.listener.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.websocket.DeploymentException;
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
    
    @Inject
    private WebsocketClient websocketClient;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext context) {
        try {
            websocketClient.connect();
            log.info("========= Notes client started =========");
        } catch (DeploymentException | IOException ex) {
            log.severe(ex.getMessage());
        }
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) ServletContext context) {
        log.info("========= Notes client died =========");
    }
}