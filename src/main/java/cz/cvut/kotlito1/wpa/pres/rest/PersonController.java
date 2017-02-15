/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.rest;

import cz.cvut.kotlito1.wpa.pres.model.Person;
import cz.cvut.kotlito1.wpa.pres.service.PersonService;
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
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService perService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Person> getAll() {
        return perService.findAllPersons();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findById(@PathVariable("id") Integer id){
            Person person = perService.findPersonById(id);
            if (person != null){
            return new ResponseEntity<>(person, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/login/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findByLogin(@PathVariable("name") String name){
            Person person  = perService.findByLogin(name);
            if (person != null){
                return new ResponseEntity<>(person, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person){           //wrapper or dto better?
            
        if (person != null){
            perService.persistPerson(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findByLogin(@PathVariable("id") Integer id){
            Person person  = perService.findPersonById(id);
            if (person != null){
                perService.removePerson(person);
                return new ResponseEntity<>(person, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    
}
