/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.service;

import cz.cvut.kotlito1.wpa.pres.dao.ReservationDao;
import cz.cvut.kotlito1.wpa.pres.exception.ServiceException;
import cz.cvut.kotlito1.wpa.pres.model.Reservation;
import java.util.Collection;
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
public class ReservationService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ReservationService.class);
    
    @Autowired
    private ReservationDao resDao;
    
    @Transactional
    public Collection<Reservation> findAllReservations(){
        try{
            return resDao.findAll();
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service findall exception.");
        }
    }
    
    @Transactional
    public Reservation findById(Integer id){
        try{
            return resDao.find(id);
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service findbyid exception.");
        }
    }
    
    @Transactional
    public Collection<Reservation> findByOwnerId(Integer id){
        try{
            return resDao.findAllByPersonId(id);
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service findbyowner exception.");
        }
    }
    
    @Transactional
    public Collection<Reservation> findByEventId(Integer id){
        try{
            return resDao.finadAllByEventId(id);
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service findbyevent exception.");
        }
    }
    
    @Transactional
    public Reservation createReservation(Reservation res){
        try{
            resDao.persist(res);
            return res;
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service create exception.");
        }
    }
    
    @Transactional
    public Reservation deleteReservation(Reservation res){
        try{
            resDao.remove(res);
            return res;
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service delete exception.");
        }
    }

    @Transactional
    public Reservation delete(Integer id) {
        try{
            Reservation res = resDao.find(id);
            resDao.remove(res);
            return res;
        }catch(RuntimeException rex){
            LOG.error("Service >>"+this.getClass().getSimpleName()+" exception");
            throw new ServiceException("Service delete exception.");
        }
    }
}
