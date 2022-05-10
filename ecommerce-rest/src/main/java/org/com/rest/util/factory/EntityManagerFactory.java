package org.com.rest.util.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class EntityManagerFactory {
    private static volatile EntityManagerFactory instance = null;
    private static volatile EntityManager entityManager=null;
        
    
    private EntityManagerFactory(){
        if(instance != null){
            throw new RuntimeException();
        }
    }

    public static EntityManagerFactory getInstance(){
        if(instance == null){
            synchronized(EntityManagerFactory.class){
                if(instance == null){
                    instance=new EntityManagerFactory();
                }
            }
        }
        return instance;
    }

    public EntityManager getIntityManager(){
        if(entityManager == null){
            synchronized(EntityManagerFactory.class){
                if(entityManager == null){
                    entityManager=Persistence.createEntityManagerFactory("ecommerce").createEntityManager();
                }
            }
        }
        return entityManager;
    }
}
