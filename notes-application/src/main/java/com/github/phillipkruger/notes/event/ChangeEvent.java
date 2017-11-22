package com.github.phillipkruger.notes.event;

import com.github.phillipkruger.notes.Note;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JAXB 2.2, Bean Validation 1.1, Lombok
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Data @NoArgsConstructor @AllArgsConstructor
@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
public class ChangeEvent {
    @NotNull @XmlElementRef
    private Note note;
    @NotNull @XmlAttribute
    private String type;
}
