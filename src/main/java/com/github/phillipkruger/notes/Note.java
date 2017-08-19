package com.github.phillipkruger.notes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JAXB 2.2, Bean Validation 1.1, JPA 2.1 and Lombok
 * 
 * Demonstrate how to easy create a POJO with Lombok that can serialize to XML and Json with JAXB, and also persist to database with JPA
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = Note.QUERY_FIND_ALL, query = "SELECT n FROM Note n"),
    @NamedQuery(name = Note.QUERY_FIND_BY_TITLE, query = "SELECT n FROM Note n WHERE n.title=:title"),
    @NamedQuery(name = Note.QUERY_COUNT_BY_TITLE, query = "SELECT count(n) FROM Note n WHERE n.title=:title")
})
public class Note implements Serializable {
    private static final long serialVersionUID = -8531040143398373846L;
    public static final String QUERY_FIND_ALL = "Note.findAll";
    public static final String QUERY_FIND_BY_TITLE = "Note.findByTitle";
    public static final String QUERY_COUNT_BY_TITLE = "Note.countByTitle";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @XmlAttribute(required=true)
    private Long id;
    
    @NotNull @XmlAttribute(required=false) 
    @Temporal(TemporalType.TIMESTAMP)
    @Past
    private Date created = new Date();
    
    @NotNull @XmlAttribute(required=false) 
    @Temporal(TemporalType.TIMESTAMP)
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