package com.github.phillipkruger.notes;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data @NoArgsConstructor @AllArgsConstructor
@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
public class Note implements Serializable {
    private static final long serialVersionUID = -8531040143398373846L;
    
    @NotNull @XmlAttribute(required=false) 
    private Date created = new Date();
    @NotNull @XmlAttribute(required=false) 
    private Date lastUpdated = new Date();
    @NotNull @XmlAttribute(required=true)
    private String title;
    @NotNull @XmlAttribute(required=true) 
    private String text;
    
    public Note(@NotNull String title, @NotNull String text){
        this.created = new Date();
        this.lastUpdated = new Date();
        this.title = title;
        this.text = text;
    }
    
}
