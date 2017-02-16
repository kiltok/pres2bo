/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.rest;

import cz.cvut.kotlito1.wpa.pres.model.Reservation;
import cz.cvut.kotlito1.wpa.pres.service.ReservationService;
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
@RequestMapping("/reservations")
public class ReservationController {
    
    @Autowired
    private ReservationService resService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Reservation>> getAll() {
        try{
            return new ResponseEntity<>(resService.findAllReservations(), HttpStatus.OK); 
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
        }  
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> findById(@PathVariable("id") Integer id){
        try{
            Reservation res = resService.findById(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Reservation>> findByOwnerId(@PathVariable("id") Integer id){
        try{
            Collection<Reservation> resColl = resService.findByOwnerId(id);
            return new ResponseEntity<>(resColl, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Reservation>> findByEventId(@PathVariable("id") Integer id){
        try{
            Collection<Reservation> resColl = resService.findByEventId(id);
            return new ResponseEntity<>(resColl, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation res){
        try{
            resService.createReservation(res);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@PathVariable("id") Integer id){
        try{
            Reservation res = resService.delete(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(RuntimeException rex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    
    
}
