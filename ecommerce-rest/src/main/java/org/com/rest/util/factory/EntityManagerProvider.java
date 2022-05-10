package org.com.rest.util.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {

    private static EntityManagerProvider provider;
    private EntityManagerFactory factory;
    private EntityManagerProvider() {
        factory=Persistence.createEntityManagerFactory("ecommerce");

    }


    public static EntityManagerProvider getInstance(){
        if(provider==null){
            provider=new EntityManagerProvider();
        }
        return provider;
    }

    public EntityManager getEntityManager(){

        return factory.createEntityManager();
    }
    
    
}
