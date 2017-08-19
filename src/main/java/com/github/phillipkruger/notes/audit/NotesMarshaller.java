package com.github.phillipkruger.notes.audit;

import com.github.phillipkruger.notes.Note;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * JAXB 2.2
 * Marshall and unmarshall Notes
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Stateless
public class NotesMarshaller {

    public byte[] marshall(Note note) {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(baos)){

            Marshaller m = NOTE_CONTEXT.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(note, bos);
            bos.flush();
            return baos.toByteArray();
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public Note unmarshall(byte[] message){
        try(ByteArrayInputStream bais = new ByteArrayInputStream(message)){
            Unmarshaller u = NOTE_CONTEXT.createUnmarshaller();
            return (Note) u.unmarshal(bais);
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static final JAXBContext NOTE_CONTEXT;
    static {
        try {
            NOTE_CONTEXT = JAXBContext.newInstance(Note.class);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
}
