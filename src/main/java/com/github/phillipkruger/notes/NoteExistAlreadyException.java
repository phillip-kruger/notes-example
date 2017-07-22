package com.github.phillipkruger.notes;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoteExistAlreadyException extends Exception {

    public NoteExistAlreadyException(String string) {
        super(string);
    }

    public NoteExistAlreadyException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public NoteExistAlreadyException(Throwable thrwbl) {
        super(thrwbl);
    }

    public NoteExistAlreadyException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
}
