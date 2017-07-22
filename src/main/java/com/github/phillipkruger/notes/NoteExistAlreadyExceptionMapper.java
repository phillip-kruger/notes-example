package com.github.phillipkruger.notes;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoteExistAlreadyExceptionMapper implements ExceptionMapper<NoteExistAlreadyException> {
    
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(NoteExistAlreadyException noteExistAlreadyException) {
        return Response.status(Response.Status.CONFLICT).header("reason",noteExistAlreadyException.getMessage()).build();
    }
}