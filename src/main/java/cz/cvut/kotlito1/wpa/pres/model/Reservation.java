/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Entity
public class Reservation implements Serializable{
    
    //identified by event/user pk combination
    @EmbeddedId
    private ReservationPK id;
    
    @ManyToOne
    @JoinColumn(name="EVENT_FK", insertable=false, updatable=false)
    private SocialEvent eventReserved;
    
    @ManyToOne
    @JoinColumn(name="PERSON_FK", insertable=false, updatable=false)
    private Person owner;
    
    @Column(nullable=false)
    private int resSize;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastmodDate;

    public Reservation() {
    }

    public ReservationPK getId() {
        return id;
    }

    public void setId(ReservationPK id) {
        this.id = id;
    }

    public SocialEvent getEventReserved() {
        return eventReserved;
    }

    public void setEventReserved(SocialEvent eventReserved) {
        this.eventReserved = eventReserved;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getResSize() {
        return resSize;
    }

    public void setResSize(int resSize) {
        this.resSize = resSize;
    }

    public Date getLastmodDate() {
        return lastmodDate;
    }

    public void setLastmodDate(Date lastmodDate) {
        this.lastmodDate = lastmodDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.eventReserved);
        hash = 29 * hash + Objects.hashCode(this.owner);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.eventReserved, other.eventReserved)) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return true;
    }
    
    
    //Composite key implementation through embedded class
    @Embeddable
    public static class ReservationPK implements Serializable{
        @Column(name="EVENT_FK")
        protected int eventId;
        
        @Column(name="PERSON_FK")
        protected int personId;

        public ReservationPK() {
        }

        public ReservationPK(int eventId, int personId) {
            this.eventId = eventId;
            this.personId = personId;
        }

        public int getEventId() {
            return eventId;
        }

        public void setEventId(int eventId) {
            this.eventId = eventId;
        }

        public int getPersonId() {
            return personId;
        }

        public void setPersonId(int personId) {
            this.personId = personId;
        }
        
        

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 59 * hash + this.eventId;
            hash = 59 * hash + this.personId;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ReservationPK other = (ReservationPK) obj;
            if (this.eventId != other.eventId) {
                return false;
            }
            if (this.personId != other.personId) {
                return false;
            }
            return true;
        }
    }
}
