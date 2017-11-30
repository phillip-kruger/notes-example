package com.github.phillipkruger.notes.controller;

import java.util.Arrays;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import lombok.extern.java.Log;

/**
 * JSF 2.2
 * Converter for tags
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@FacesConverter(value="com.github.phillipkruger.notes.controller.TagsConverter",forClass=List.class)
public class TagsConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null)return null;
        return Arrays.asList(value.split(SPLIT_REGEX));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null)return null;
        List<String> tags = (List<String>)value;
        if(tags.isEmpty())return EMPTY;
        
        return String.join(COMMA, tags);
    }
    
    private static final String COMMA = ",";
    private static final String EMPTY = "";
    private static final String SPLIT_REGEX = "\\s*,\\s*";
}
