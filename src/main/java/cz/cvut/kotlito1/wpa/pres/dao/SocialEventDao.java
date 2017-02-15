/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.dao;

import cz.cvut.kotlito1.wpa.pres.model.SocialEvent;
import java.util.Collection;
import java.util.Date;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Repository
public class SocialEventDao extends BasicDao<SocialEvent> {
    
    private static final Logger LOG = LoggerFactory.getLogger(UserAccountDao.class);
    
    @Autowired
    private AddressDao addrDao;

    public SocialEventDao() {
        super(SocialEvent.class);
    }
    
    public SocialEvent findByCode(String code){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.findByCode", SocialEvent.class);
            return (SocialEvent) tq.setParameter("code", code).getSingleResult();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find by code error >>"+rex.toString());
            return null;
        }
    }
    
    public Collection<SocialEvent> findByLabel(String label){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.findByLabel", SocialEvent.class);
            return tq.setParameter("label", label).getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find by code error >>"+rex.toString());
            return null;
        }
    }
    
    public Collection<SocialEvent> findActive(Date value){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.active", SocialEvent.class);
            return tq.setParameter("val", value).getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find before date error >>"+rex.toString());
            return null;
        }
    }
    
    public Collection<SocialEvent> findRunning(Date value){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.running", SocialEvent.class);
            return tq.setParameter("val", value).getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find after date error >>"+rex.toString());
            return null;
        }
    }
    
    public Collection<SocialEvent> findFinished(Date value1){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.finished", SocialEvent.class);
            return tq.setParameter("val1", value1).getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find between dates error >>"+rex.toString());
            return null;
        }
    }
    
    public Collection<SocialEvent> findEventsAtAdress(Integer addrId){
        try{
            TypedQuery tq = em.createQuery("SELECT sev FROM SocialEvent sev WHERE sev.location.id=:addrId", SocialEvent.class);
            return tq.getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO query find events at address id error >>"+rex.toString());
            return null;
        }
    }
    
    @Override
    public void remove(SocialEvent sev){
        //null creator+contact
        //find out how many active references are bound to location if just one from this event u can remove it safely
        //remove the event
        //remove all reservations bound to this event walking through reservations fk_event
        if(this.findEventsAtAdress(sev.getLocation().getId()).size() == 1){
            em.remove(sev.getLocation());
        }
        super.remove(sev);
    }
    
    @Override
    public void persist(SocialEvent sev){
        //check out if address exists and persist it
        //persist the event
    }
    
    
    
}
