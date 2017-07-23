package com.github.phillipkruger.notes;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Translate ConstraintViolationException (Bean validation) to HTTP 412 (Precondition failed)
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ConstraintViolationException constraintViolationException) {
        return Response.status(Response.Status.PRECONDITION_FAILED).header("reason", constraintViolationException.getMessage()).build();
    }
}