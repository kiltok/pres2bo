/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@NamedQueries({
    @NamedQuery(name="SocialEvent.findByLabel", query="SELECT se FROM SocialEvent se WHERE se.label LIKE :label"),
    @NamedQuery(name="SocialEvent.findByCode", query="SELECT se FROM SocialEvent se WHERE se.code =:code"),
    @NamedQuery(name="SocialEvent.active", query="SELECT se FROM SocialEvent se WHERE se.deadlineDate > :val"),
    @NamedQuery(name="SocialEvent.running", query="SELECT se FROM SocialEvent se WHERE  se.startDate < :val AND se.endDate > :val"),
    @NamedQuery(name="SocialEvent.finished", query="SELECT se FROM SocialEvent se WHERE  se.endDate < :val1 "),
})
@Entity
public class SocialEvent implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    private String label;
    private String code;
    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date endDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date deadlineDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date modifiedDate;
    
    
    @ManyToOne(optional=true)
    private Address location;
    
    @ManyToOne
    private Person creator;
    
    @ManyToOne
    private Person contact;

    public SocialEvent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public Person getContact() {
        return contact;
    }

    public void setContact(Person contact) {
        this.contact = contact;
    }    
}
