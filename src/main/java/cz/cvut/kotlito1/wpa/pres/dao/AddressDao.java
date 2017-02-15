/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.dao;

import cz.cvut.kotlito1.wpa.pres.model.Address;
import cz.cvut.kotlito1.wpa.pres.model.City;
import cz.cvut.kotlito1.wpa.pres.model.Country;
import java.util.Objects;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 */
@Repository
public class AddressDao extends BasicDao<Address>{

    private static final Logger LOG = LoggerFactory.getLogger(BasicDao.class);
    
    public AddressDao() {
        super(Address.class);
    }
    
    @Override
    public void remove(Address address){
        Objects.requireNonNull(address);
        try{
            Address remAddress = em.merge(address);
            City remCity = remAddress.getCity();
            Country remCountry = remAddress.getCountry();
            if (remCity != null){
               TypedQuery tq = em.createNamedQuery("Address.findAllinCity", Address.class).setParameter("cityId", remCity.getId());
               if(tq.getResultList().size() == 1) //only this instance holds last city reference
               {
                   remAddress.setCity(null);
                   em.remove(remCity);
               }
            }
            if (remCountry != null){
               TypedQuery tq = em.createNamedQuery("Address.findAllinCountry", Address.class).setParameter("countryId", remCountry.getId());
               if(tq.getResultList().size() == 1) //only this instance holds last city reference
               {
                   remAddress.setCountry(null);
                   em.remove(remCountry);
               }
            }
            em.remove(remAddress);
        }catch(RuntimeException rex){
            LOG.error("Address DAO remove error. >> "+rex.toString());
        }   
    }
    
    @Override
    public void persist(Address address){
        Objects.requireNonNull(address);
        try{
            if(address.getCity() != null)
                em.persist(address.getCity());
            if(address.getCountry() != null)
                em.persist(address.getCountry());
            em.persist(address);
        }catch(RuntimeException rex){
            LOG.error("Address DAO persist error. >> "+rex.toString());
        }
    }
}
