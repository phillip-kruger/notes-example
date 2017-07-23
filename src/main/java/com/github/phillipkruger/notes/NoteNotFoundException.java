package com.github.phillipkruger.notes;

import lombok.NoArgsConstructor;

/**
 * Exception when note could not be found
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@NoArgsConstructor
public class NoteNotFoundException extends Exception {

    public NoteNotFoundException(String string) {
        super(string);
    }

    public NoteNotFoundException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public NoteNotFoundException(Throwable thrwbl) {
        super(thrwbl);
    }

    public NoteNotFoundException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
