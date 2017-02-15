/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.service;

import cz.cvut.kotlito1.wpa.pres.dao.SocialEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Service
public class SocialEventService {
    
    @Autowired
    private SocialEventDao sevDao;
    
    
    
}
