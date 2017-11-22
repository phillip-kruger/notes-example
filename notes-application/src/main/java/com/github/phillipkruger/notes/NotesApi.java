package com.github.phillipkruger.notes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS 2.0, JAX-WS 2.2, JAXB 2.2, Bean validation 1.1
 * 
 * The API for outside consumption. 
 * Just wrapping the actual implementation with SOAP and REST.
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Path("/note")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Api(value = "Notes service")
@WebService
public class NotesApi {
    
    @Inject 
    private NotesService notesService;
    
    @POST
    @Path("/{title}")
    @ApiOperation(value = "Create a new note", notes = "This will create a new note under a certain title, only if it does not exist already")
    @WebMethod
    public Note createNote(@WebParam @PathParam("title") String title,@NotNull @WebParam String text) throws NoteExistAlreadyException{
        return notesService.createNote(title, text);
    }
    
    @GET
    @Path("/{title}")
    @ApiOperation(value = "Retrieve a note", notes = "This will find the note with a certain title, if it exist")
    @WebMethod
    public Note getNote(@PathParam("title") @WebParam String title) throws NoteNotFoundException{
        return notesService.getNote(title);
    }
    
    @DELETE
    @Path("/{title}")
    @ApiOperation(value = "Delete a note", notes = "This will delete the note with a certain title, if it exist")
    @WebMethod
    public void deleteNote(@PathParam("title") @WebParam String title) throws NoteNotFoundException{
        notesService.deleteNote(title);
    }
    
    @PUT
    @ApiOperation(value = "Update a note", notes = "This will update the note with a given new note, if it exist")
    @WebMethod
    public Note updateNote(@WebParam Note note) throws NoteNotFoundException{
        return notesService.updateNote(note);
    }
    
    @GET
    @Path("/exists/{title}")
    @ApiOperation(value = "Check if note exist", notes = "This will check if a note with a certain title exist")
    @WebMethod
    public boolean exist(@WebParam @PathParam("title") String title){
        return notesService.exist(title);
    }
    
    @GET
    @ApiOperation(value = "Get all the note titles", notes = "This will return all current note titles")
    @WebMethod
    public List<String> getNoteTitles(){
        return notesService.getNoteTitles();
    }
}