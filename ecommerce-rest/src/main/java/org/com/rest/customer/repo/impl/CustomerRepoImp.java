package org.com.rest.customer.repo.impl;

import java.util.List;

import org.com.rest.customer.repo.entity.CustomersEntity;
import org.com.rest.customer.repo.interfaces.CustomerRepo;
import org.com.rest.customer.service.dto.CustomersPostDto;
import org.com.rest.order.repo.entity.OrdersEntity;
import org.com.rest.order.service.dto.OrderPostDto;
import org.com.rest.util.factory.EntityManagerProvider;

import jakarta.persistence.EntityManager;

public class CustomerRepoImp implements CustomerRepo{
    EntityManagerProvider entityManagerProvider = EntityManagerProvider.getInstance();
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    @Override
    public CustomersEntity findCustomerById(Integer id) {
        return entityManager.find(CustomersEntity.class, id);
        
    }

    @Override
    public List<CustomersEntity> getCustomers() {
        return entityManager.createQuery("from CustomersEntity", CustomersEntity.class).getResultList();

    }

    @Override
    public void insertCustomer(CustomersPostDto customerPostDto) {
        CustomersEntity customersEntity=new CustomersEntity();
        customersEntity.setUsername(customerPostDto.getUsername());
        customersEntity.setEmail(customerPostDto.getEmail());
        customersEntity.setPass(customerPostDto.getPass());
        customersEntity.setAddress(customerPostDto.getAddress());
        customersEntity.setDob(customerPostDto.getDob());
        customersEntity.setInterests(customerPostDto.getInterests());
        customersEntity.setIsMale(customerPostDto.getIsMale());
        customersEntity.setJob(customerPostDto.getJob());
        entityManager.getTransaction().begin();
        entityManager.merge(customersEntity);
        entityManager.getTransaction().commit();
      
    }

    @Override
    public CustomersEntity updateCustomer(int id, CustomersPostDto customerPostDto) {
        CustomersEntity customersEntity=entityManager.find(CustomersEntity.class, id);
        customersEntity.setUsername(customerPostDto.getUsername());
        customersEntity.setEmail(customerPostDto.getEmail());
        customersEntity.setPass(customerPostDto.getPass());
        customersEntity.setAddress(customerPostDto.getAddress());
        customersEntity.setDob(customerPostDto.getDob());
        customersEntity.setInterests(customerPostDto.getInterests());
        customersEntity.setIsMale(customerPostDto.getIsMale());
        customersEntity.setJob(customerPostDto.getJob());
        entityManager.getTransaction().begin();
        entityManager.merge(customersEntity);
        entityManager.getTransaction().commit();
        return customersEntity;

    }

    @Override
    public CustomersEntity removeCustomer(int id) {
        CustomersEntity customersEntity=entityManager.find(CustomersEntity.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(customersEntity);
        entityManager.getTransaction().commit();
        return customersEntity;
    }

    @Override
    public Long getCustomerCount() {
        // TODO Auto-generated method stub
        return null;
    }

   

  
    
}
