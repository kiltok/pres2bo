/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.rest;

import cz.cvut.kotlito1.wpa.pres.model.UserAccount;
import cz.cvut.kotlito1.wpa.pres.service.UserAccountService;
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
@RequestMapping("/accounts")
public class UserAccountController {
    
    @Autowired
    UserAccountService uaccService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<UserAccount> getAll() {
        return uaccService.findAll();
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAccount> create(@RequestBody UserAccount uacc){
            uaccService.persist(uacc);
        if (uacc != null){
            
            return new ResponseEntity<>(uacc, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAccount> find(@PathVariable("id") Integer id){
            UserAccount uacc = uaccService.find(id);
            if (uacc != null){
            return new ResponseEntity<>(uacc, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/login/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAccount> find(@PathVariable("name") String name){
            UserAccount uacc  = uaccService.findByLogin(name);
            if (uacc != null){
            return new ResponseEntity<>(uacc, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    
}
