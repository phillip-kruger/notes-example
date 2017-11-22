package com.github.phillipkruger.notes.listener.client;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

/**
 * The endpoint that receive messages
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Dependent
@ClientEndpoint
public class MessageClientEndpoint {

    @Inject
    private Event<String> broadcaster;
    
    @OnMessage
    public void onMessage(String message) {
        broadcaster.fire(message);
    }

}