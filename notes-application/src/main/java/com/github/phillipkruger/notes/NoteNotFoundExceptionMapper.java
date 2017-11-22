package com.github.phillipkruger.notes;

import java.util.logging.Level;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.java.Log;

/**
 * Translate NoteNotFoundException to HTTP Not Found
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Provider
public class NoteNotFoundExceptionMapper implements ExceptionMapper<NoteNotFoundException> {
    
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(NoteNotFoundException noteNotFoundException) {
        log.log(Level.WARNING, "{0} - translating to [{1}]", new Object[]{noteNotFoundException.getMessage(), Response.Status.NOT_FOUND});
        return Response.status(Response.Status.NOT_FOUND).header("reason",noteNotFoundException.getMessage()).build();
    }
}