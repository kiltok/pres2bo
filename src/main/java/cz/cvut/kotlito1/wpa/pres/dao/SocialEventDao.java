/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.dao;

import cz.cvut.kotlito1.wpa.pres.exception.PersistenceException;
import cz.cvut.kotlito1.wpa.pres.model.Address;
import cz.cvut.kotlito1.wpa.pres.model.Person;
import cz.cvut.kotlito1.wpa.pres.model.SocialEvent;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
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
    
    private static final Logger LOG = LoggerFactory.getLogger(SocialEventDao.class);
    
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
            throw new PersistenceException("SocialEvent find by code failed.");
        }
    }
    
    public Collection<SocialEvent> findByLabel(String label){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.findByLabel", SocialEvent.class);
            return tq.setParameter("label", label).getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find by code error >>"+rex.toString());
            throw new PersistenceException("SocialEvent label exception.");
        }
    }
    
    public Collection<SocialEvent> findActive(Date value){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.active", SocialEvent.class);
            return tq.setParameter("val", value).getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find before date error >>"+rex.toString());
            throw new PersistenceException("SocialEvent find active failed");
        }
    }
    
    public Collection<SocialEvent> findRunning(Date value){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.running", SocialEvent.class);
            return tq.setParameter("val", value).getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find after date error >>"+rex.toString());
            throw new PersistenceException("SocialEvent find running failed.");
        }
    }
    
    public Collection<SocialEvent> findFinished(Date value1){
        try{
            TypedQuery tq = em.createNamedQuery("SocialEvent.finished", SocialEvent.class);
            return tq.setParameter("val1", value1).getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO named query find between dates error >>"+rex.toString());
            throw new PersistenceException("SocialEvent find finished failed.");
        }
    }
    
    public Collection<SocialEvent> findEventsAtAdress(Integer addrId){
        try{
            TypedQuery tq = em.createQuery("SELECT sev FROM SocialEvent sev WHERE sev.location.id=:addrId", SocialEvent.class);
            return tq.getResultList();
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO query find events at address id error >>"+rex.toString());
            throw new PersistenceException("SocialEvent find events at address failed.");
        }
    }
    
    @Override
    public void remove(SocialEvent sev){
        //null creator+contact
        try{
        
        sev.setContact(null);
        sev.setCreator(null);
        if(sev.getLocation() != null){
            Address remAddr = em.find(Address.class, sev.getLocation());
            if(remAddr != null){      
                TypedQuery tq = em.createQuery("SELECT COUNT(per) FROM Person per WHERE per.address.id = :addrId", Person.class);
                Long countPersons =(Long) tq.setParameter("addrId", remAddr.getId()).getSingleResult();
                tq = em.createQuery("SELECT COUNT(sev) FROM SocialEvent sev WHERE sev.location.id = :addrId", SocialEvent.class);
                Long countEvents =(Long) tq.setParameter("addrId", remAddr.getId()).getSingleResult();
                if(countEvents == 1 && countPersons == 0){  // event address referenced only once and exactly from this event
                    em.remove(remAddr);
                    sev.setLocation(null);
                }
            }
        }
        super.remove(sev);
        }catch(RuntimeException rex){
            LOG.error("Social Event DAO remove error >>"+rex.toString());
            throw new PersistenceException("SocialEvent find events at address failed.");
        }
        
    }
    
    @Override
    public void persist(SocialEvent sev){
        //check out if address exists and persist it
        //persist the event
        Objects.requireNonNull(sev);
        if(sev.getContact() != null && sev.getContact().getId() != null){
            Person existingContact = em.getReference(Person.class, sev.getContact().getId());
            if (existingContact != null){
                sev.setContact(existingContact); 
            }
        }
        if(sev.getCreator() != null && sev.getCreator().getId() != null){
            Person existingCreator = em.getReference(Person.class, sev.getCreator().getId());
            if(existingCreator != null){
                sev.setCreator(existingCreator); 
            }
        }
        if(sev.getLocation() != null && sev.getLocation().getId() != null){
            Address existingAddress = em.getReference(Address.class, sev.getLocation().getId());
            if(existingAddress != null){
                sev.setLocation(existingAddress);
            }
        }
        em.persist(sev);
    }
}
