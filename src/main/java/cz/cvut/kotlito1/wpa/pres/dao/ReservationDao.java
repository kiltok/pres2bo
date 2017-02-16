/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.dao;

import cz.cvut.kotlito1.wpa.pres.exception.PersistenceException;
import cz.cvut.kotlito1.wpa.pres.model.Person;
import cz.cvut.kotlito1.wpa.pres.model.Reservation;
import cz.cvut.kotlito1.wpa.pres.model.SocialEvent;
import java.util.Collection;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Repository
public class ReservationDao extends BasicDao<Reservation> {
    
    private static final Logger LOG = LoggerFactory.getLogger(ReservationDao.class);

    public ReservationDao() {
        super(Reservation.class);
    }
    
    
    @Override
    public void persist(Reservation res){
        try{
            if(res.getEventReserved() != null && res.getEventReserved().getId() != null){
                SocialEvent existingEvent = em.getReference(SocialEvent.class, res.getEventReserved().getId());
                if(existingEvent != null){
                    res.setEventReserved(existingEvent);
                }
            }
            if(res.getOwner() != null && res.getOwner().getId() != null ){
                Person existingOwner = em.getReference(Person.class, res.getOwner().getId());
                if(existingOwner != null){
                    res.setOwner(existingOwner);
                }
            }
            em.persist(res);
        }catch(RuntimeException rex){
            LOG.error(this.getClass().getSimpleName()+" >> remove reservation error." + rex.toString());
            throw new PersistenceException("Reservation dao");
        }  
    }
    
    @Override
    public void remove(Reservation res){
        try{
            Reservation remRes = em.merge(res);
            if(remRes != null){
                remRes.setOwner(null);
                remRes.setEventReserved(null);
                em.remove(remRes);
            }
        }catch(RuntimeException rex){
            LOG.error(this.getClass().getSimpleName()+" >> remove reservation error." + rex.toString());
            throw new PersistenceException("Reservation dao");
        }
    }
    
    public Collection<Reservation> findAllByPersonId(Integer id){
        try{
            TypedQuery tq = em.createQuery("SELECT res FROM Reservation res WHERE res.owner.id = :perId",Reservation.class).setParameter("perId", id);
            return tq.getResultList();
        }catch(RuntimeException rex){
            LOG.error(this.getClass().getSimpleName()+" >> find all by person id error." + rex.toString());
            throw new PersistenceException("Reservation dao");
        }
    }
    
    public Collection<Reservation> finadAllByEventId(Integer id){
        try{
            TypedQuery tq = em.createQuery("SELECT res FROM Reservation res WHERE res.eventReserved.id = :sevId",Reservation.class).setParameter("sevId", id);
            return tq.getResultList();
        }catch(RuntimeException rex){
            LOG.error(this.getClass().getSimpleName()+" >> find all by event id error." + rex.toString());
            throw new PersistenceException("Reservation dao");
        }
    }
    
}
