/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotlito1.wpa.pres.dao;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomas Kotlik <kotlito1 at fel.cvut.cz>
 * @param <T>
 */
public abstract class BasicDao<T> {
    
    private static final Logger LOG = LoggerFactory.getLogger(BasicDao.class);
    
    @PersistenceContext
    protected EntityManager em;
    
    private final Class<T> type;
    
    protected BasicDao(Class<T> type){
        this.type = type;
    }
    
    public T find(Integer id){
        return em.find(type, id);
    }
    
    public Collection<T> findAll(){
        try{
            TypedQuery tquery = em.createQuery("SELECT entity FROM "+type.getSimpleName()+" entity", type);
            return tquery.getResultList();
        }finally{
            em.close();
        }
    }
    
    public void persist(T entity){
        Objects.requireNonNull(entity);
        try{
        em.persist(entity);
        }catch(RuntimeException rex){
            LOG.error("BasicDAO persist entity failed >>"+rex.toString());
        }
    }
    
    public void persist(Collection<T> entities){
         Objects.requireNonNull(entities);
        try{
        if (entities.isEmpty())
            return;
        entities.forEach((entity) -> this.persist(entity));
        }catch(RuntimeException rex){
            LOG.error("BasicDAO persist entity failed >>"+rex.toString());
        }
    }
    
    public void remove(T entity){
        Objects.requireNonNull(entity);
        try{
            T torem = em.merge(entity);
            if(torem != null){
                em.remove(torem);
            }
        }catch(RuntimeException rex){
            LOG.error("BasicDAO remove entity failed >>"+rex.toString());
        }
    }
    
    public T update(T entity){
        Objects.requireNonNull(entity);
        try{
            return em.merge(entity);
        }catch(RuntimeException rex){
            LOG.error("BasicDAO update entity failed >>"+rex.toString());
            return null;
        }
    }
    
}
