/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.service;

import cz.cvut.kotlito1.wpa.pres.dao.SocialEventDao;
import cz.cvut.kotlito1.wpa.pres.dao.UserAccountDao;
import cz.cvut.kotlito1.wpa.pres.exception.ServiceException;
import cz.cvut.kotlito1.wpa.pres.model.SocialEvent;
import java.util.Collection;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Service
public class SocialEventService {
    
    private static final Logger LOG = LoggerFactory.getLogger(SocialEventService.class);
    
    @Autowired
    private SocialEventDao sevDao;
    
    @Transactional
    public Collection<SocialEvent> findAllEvents(){
        return sevDao.findAll();
    }
    
    @Transactional
    public Collection<SocialEvent> findByLabel(String label){
        try {
            return sevDao.findByLabel(label);
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service label exception.");
        }
    }
    
    @Transactional
    public SocialEvent findById(Integer id){
        try{
            return sevDao.find(id);
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service find by id exception.");
        }
    }
    
    @Transactional
    public Collection<SocialEvent> findActiveEvents(){
        try{
            Date today = new Date();
            return sevDao.findActive(today);
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service find active events exception.");
        }
    }
    
    @Transactional
    public Collection<SocialEvent> findRunningEvents(){
        try{
            Date today = new Date();
            return sevDao.findFinished(today);
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service find running exception.");
        }
    }
    
    @Transactional
    public Collection<SocialEvent> findFinishedEvents(){
        try{
            Date today = new Date();
            return sevDao.findFinished(today);
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" find exception");
            throw new ServiceException("Service find finished exception.");
        }
    }
    
    @Transactional
    public SocialEvent createEvent(SocialEvent sev){
        try{
            //validate entity
            //call dao to persis it
            sevDao.persist(sev);
            return sev;
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" create exception");
            throw new ServiceException("Service label exception.");
        }
    }
    
    @Transactional
    public SocialEvent deleteEvent(Integer id){
        try{
            SocialEvent sev = sevDao.find(id);
            sevDao.remove(sev);
            return sev;
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" delete exception");
            throw new ServiceException("Service label exception.");
        }
    }
}
