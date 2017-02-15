/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.service;

import cz.cvut.kotlito1.wpa.pres.dao.UserAccountDao;
import cz.cvut.kotlito1.wpa.pres.model.UserAccount;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Service
public class UserAccountService {
    
    @Autowired
    private UserAccountDao uaccDao;
    
    @Transactional(readOnly=true)
    public UserAccount find(Integer id){
        return uaccDao.find(id);
    }
    
    @Transactional(readOnly = true)
    public Collection<UserAccount> findAll() {
        return uaccDao.findAll();
    }
    
    @Transactional
    public void remove(UserAccount uacc){
        uaccDao.remove(uacc);
    }
    
    @Transactional
    public void persist(UserAccount uacc) {
        uaccDao.persist(uacc);
    }
    
    @Transactional
    public UserAccount findByLogin(String login){
        return uaccDao.findByLogin(login);
    }
    
}
