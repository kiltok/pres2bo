/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.dao;

import cz.cvut.kotlito1.wpa.pres.model.UserAccount;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Repository
public class UserAccountDao extends BasicDao<UserAccount> {
    
    private static final Logger LOG = LoggerFactory.getLogger(UserAccountDao.class);

    public UserAccountDao() {
        super(UserAccount.class);
    }
    
    public UserAccount findByLogin(String login){
        try{
            TypedQuery tq = em.createQuery("SELECT uacc FROM UserAccount uacc WHERE uacc.login = :login ", UserAccount.class);
            tq = tq.setParameter("login", login);
            return (UserAccount) tq.getSingleResult();
        }catch(RuntimeException rex){
            LOG.error("UserAccount DAO find by login failed >>"+rex.toString());
            return null;
        }
    }
 
       
}
