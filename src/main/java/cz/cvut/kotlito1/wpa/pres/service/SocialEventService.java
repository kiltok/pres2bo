/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.service;

import cz.cvut.kotlito1.wpa.pres.dao.SocialEventDao;
import cz.cvut.kotlito1.wpa.pres.model.SocialEvent;
import java.util.Collection;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Service
public class SocialEventService {
    
    @Autowired
    private SocialEventDao sevDao;
    
    @Transactional
    public Collection<SocialEvent> findAllEvents(){
        return sevDao.findAll();
    }
    
    @Transactional
    public Collection<SocialEvent> findByLabel(String label){
        Objects.requireNonNull(label);
        
    }
    
    public SocialEvent findById(Integer id){
        
    }
    
    public Collection<SocialEvent> findActiveEvents(){
        
    }
    
    public Collection<SocialEvent> findRunningEvents(){
        
    }
    
    public Collection<SocialEvent> findFinishedEvents(){
        
    }
    
    public SocialEvent createEvent(SocialEvent sev){
        
    }
    
    public SocialEvent deleteEvent(Integer id){
        
    }
}
