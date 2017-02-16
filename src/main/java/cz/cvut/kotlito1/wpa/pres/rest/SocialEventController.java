/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.rest;

import cz.cvut.kotlito1.wpa.pres.model.SocialEvent;
import cz.cvut.kotlito1.wpa.pres.service.SocialEventService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@RestController
@RequestMapping("/events")
public class SocialEventController {
    
    @Autowired
    private SocialEventService sevService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SocialEvent>> getAll() {
        try{
            return new ResponseEntity<>(sevService.findAllEvents(), HttpStatus.OK); 
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.OK); 
        }  
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocialEvent> findById(@PathVariable("id") Integer id){
        try{
            SocialEvent sev = sevService.findById(id);    
            return new ResponseEntity<>(sev, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/find/label/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SocialEvent>> findByLabel(@PathVariable("name") String name){
        try{
            Collection<SocialEvent> sev = sevService.findByLabel(name);
            return new ResponseEntity<>(sev, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);    
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/find/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SocialEvent>> findActive(){
        try{
            Collection<SocialEvent> sev = sevService.findActiveEvents();
            return new ResponseEntity<>(sev, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);   
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/find/running", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SocialEvent>> findRunning(){
        try{
            Collection<SocialEvent> sev = sevService.findRunningEvents();
            return new ResponseEntity<>(sev, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);   
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/find/finished", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SocialEvent>> findFinished(){
        try{
            Collection<SocialEvent> sev = sevService.findFinishedEvents();
            return new ResponseEntity<>(sev, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);   
        }
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocialEvent> create(@RequestBody SocialEvent sev){           //wrapper or dto better?
        try{
            sevService.createEvent(sev);
            return new ResponseEntity<>(sev, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }    
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocialEvent> removeEvent(@PathVariable("id") Integer id){
        try{
            sevService.deleteEvent(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    
}
