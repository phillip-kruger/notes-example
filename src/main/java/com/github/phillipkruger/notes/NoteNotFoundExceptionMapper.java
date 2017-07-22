package com.github.phillipkruger.notes;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoteNotFoundExceptionMapper implements ExceptionMapper<NoteNotFoundException> {
    
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(NoteNotFoundException noteNotFoundException) {
        return Response.status(Response.Status.NOT_FOUND).header("reason",noteNotFoundException.getMessage()).build();
    }
}