/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.service;

import cz.cvut.kotlito1.wpa.pres.dao.AddressDao;
import cz.cvut.kotlito1.wpa.pres.dao.ContactDao;
import cz.cvut.kotlito1.wpa.pres.dao.PersonDao;
import cz.cvut.kotlito1.wpa.pres.dao.UserAccountDao;
import cz.cvut.kotlito1.wpa.pres.model.Person;
import cz.cvut.kotlito1.wpa.pres.model.UserAccount;
import cz.cvut.kotlito1.wpa.pres.model.util.UserRoleType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Service
public class PersonService {
    
    @Autowired
    PersonDao perDao;
    
    @Autowired 
    UserAccountDao uaccDao;
    
    @Autowired
    ContactDao contDao;
    
    @Autowired
    AddressDao addrDao;
    
    @Transactional
    public Collection<Person> findAllPersons(){
        return perDao.findAll();
    }
    
    @Transactional
    public Collection<Person> findAllPersonsByRole(UserRoleType urole){
        Collection<UserAccount> matchingAccounts = uaccDao.findByUserRole(urole);
        if (matchingAccounts.isEmpty())
            return null;
        Collection<Person> matchingPersons = new HashSet();
        for (UserAccount item : matchingAccounts){
            Person p = perDao.findByLogin(item.getLogin());     
            if (p != null)
                matchingPersons.add(p);
        }
        return matchingPersons;
    }
    
    @Transactional
    public Person findPersonById(Integer id){
       return perDao.find(id);
    }
    
    @Transactional
    public void persistPerson(Person p){
        perDao.persist(p);
    }
    
    @Transactional
    public Person updatePerson(Person p){
        return perDao.update(p);
    }
    
    @Transactional
    public void removePerson(Person p){
        
        Objects.requireNonNull(p);
        perDao.remove(p);
    }
    
    @Transactional
    public Person findByLogin(String login){
        return perDao.findByLogin(login);
    }
    
    @Transactional Person createPerson(Person pe){  //create = persist
        //find out if address already exists
        //persist person with unique address, rest is owned uniquely by person so it is cascaded
        perDao.persist(pe);
        return pe;
    }
}
