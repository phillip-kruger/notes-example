package com.github.phillipkruger.notes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

/**
 * Enum
 * 
 * Demonstrate how to use enums with JPA and JAXB etc.
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Getter
public enum NoteStyle {
    
    green("G"),
    yellow("Y"),
    white("W"),
    blue("B"),
    red("R");

    private final String style;

    NoteStyle(String style) {
        this.style = style;
    }

    public static NoteStyle fromValue(@NotNull @Size(min=1, max=10)String style){
        if(green.style.equalsIgnoreCase(getFirstChar(style))){
            return green;
        }else if(yellow.style.equalsIgnoreCase(getFirstChar(style))){
            return yellow;
        }else if(white.style.equalsIgnoreCase(getFirstChar(style))){
            return white;
        }
        return null;
    }
    
    private static String getFirstChar(String input){
        return new String(new char[]{input.charAt(0)});
    }
}