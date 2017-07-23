package com.github.phillipkruger.notes;

import java.util.logging.Level;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.java.Log;

/**
 * Translate NoteExistAlreadyException to HTTP Conflict
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Provider
public class NoteExistAlreadyExceptionMapper implements ExceptionMapper<NoteExistAlreadyException> {
    
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(NoteExistAlreadyException noteExistAlreadyException) {
        log.log(Level.WARNING, "{0} - translating to [{1}]", new Object[]{noteExistAlreadyException.getMessage(), Response.Status.CONFLICT});
        return Response.status(Response.Status.CONFLICT).header("reason",noteExistAlreadyException.getMessage()).build();
    }
}