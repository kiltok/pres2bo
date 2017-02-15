/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.dao;

import cz.cvut.kotlito1.wpa.pres.model.Address;
import cz.cvut.kotlito1.wpa.pres.model.Person;
import cz.cvut.kotlito1.wpa.pres.model.SocialEvent;
import java.util.Collection;
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
public class PersonDao extends BasicDao<Person>{

    private static final Logger LOG = LoggerFactory.getLogger(UserAccountDao.class);
    
    @Autowired
    private AddressDao addrDao;
    
    public PersonDao() {
        super(Person.class);
    }
    
    public Collection<Person> findBySurname(String surname){
        try{
            TypedQuery tq = em.createNamedQuery("Person.findBySurname", Person.class).setParameter("surname", surname);
            return tq.getResultList();
        }catch(RuntimeException rex){
            LOG.error("Person DAO named query find by surname error >>"+rex.toString());
            return null;
        }
    }
    
    public Person findByLogin(String login){
        try{
            TypedQuery tq = em.createNamedQuery("Person.findByLogin", Person.class).setParameter("login", login);
            return (Person) tq.getSingleResult();
        }catch(RuntimeException rex){
            LOG.error("Person DAO named query find by login error >>"+rex.toString());
            return null;
        }
    }
    
    public Collection<Person> findAllAtAddressId(Integer id){
        try{
            TypedQuery tq = em.createNamedQuery("Person.findByLogin", Person.class).setParameter("addrId", id);
            return tq.getResultList();
        }catch(RuntimeException rex){
            LOG.error("Person DAO named query find by login error >>"+rex.toString());
            return null;
        }
    }
    
    @Override
    public void persist(Person person){
        Objects.requireNonNull(person);
        try{
            //em.persist(person.getAddress());
            if(person.getAddress()!=null)
                addrDao.persist(person.getAddress());
            if(person.getUaccount() != null)
                em.persist(person.getUaccount());
            em.persist(person);
        }catch(RuntimeException rex){
            LOG.error("Person DAO persist error >>"+rex.toString());
        }
    }
    
    @Override
    public void remove(Person person){
        Objects.requireNonNull(person);
        try{
           Person remPerson = em.merge(person);
           Address remAddr = remPerson.getAddress();
           //how many references do we have for this person's address from person
           TypedQuery tq = em.createQuery("SELECT per FROM Person per WHERE per.address.id = :addrId", Person.class).setParameter("addrId",remAddr.getId());
           Collection<Person> addrPersonHits = tq.getResultList();
           //how many references do we have for this person's address from event
           TypedQuery tq2 = em.createQuery("SELECT sev FROM SocialEvent sev WHERE sev.location.id = :addrId", SocialEvent.class).setParameter("addrId",remAddr.getId());
           Collection<SocialEvent> addrEventHits = tq2.getResultList();
           if(addrPersonHits.size() == 1 && addrEventHits.isEmpty()){  //only this person instance holds this address and it can be removed
               addrDao.remove(remAddr);
               remPerson.setAddress(null);
           }
           em.remove(remPerson);
        }catch(RuntimeException rex){
            LOG.error("Person DAO remove error >>"+rex.toString());
        }  
    }
    
}
