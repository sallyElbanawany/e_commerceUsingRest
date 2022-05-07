package org.com.order.repo.impl;

import java.util.List;

import org.com.customer.repo.entity.CustomersEntity;
import org.com.order.repo.entity.OrdersEntity;
import org.com.order.repo.interfaces.OrderRepo;
import org.com.order.service.dto.OrderPostDto;
import org.com.util.factory.EntityManagerProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class OrderRepoImp implements OrderRepo {
    EntityManagerProvider entityManagerProvider = EntityManagerProvider.getInstance();
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    @Override
    public List<OrdersEntity> getUnSubmittedOrder(int customerId) {
        TypedQuery<OrdersEntity> query = entityManager
                .createQuery("select p from OrderEntity p where p.customer.id = :customerId and isSubmitted = :isSumb",
                        OrdersEntity.class);
        query.setParameter("isSumb", true);

        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    @Override
    public Long getOrdersCount() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrdersEntity> getOrders() {
        return entityManager.createQuery("from OrdersEntity", OrdersEntity.class).getResultList();

    }

    @Override
    public List<OrdersEntity> getOrdersByCustomerId(int customerId) {        
        TypedQuery<OrdersEntity> query = entityManager.createQuery("from OrdersEntity where customers.id=:customerID " , OrdersEntity.class)
        .setParameter("customerID",customerId);
        return query.getResultList();
    }

    @Override
    public OrdersEntity addOrder(int id, OrderPostDto orderPostDto) {
        CustomersEntity customersEntity=entityManager.find(CustomersEntity.class, id);
        OrdersEntity ordersEntity=new OrdersEntity();
        ordersEntity.setCustomers(customersEntity);
        ordersEntity.setCreatedAt(orderPostDto.getCreatedAt());
        ordersEntity.setIsSubmitted(orderPostDto.getIsSubmitted());
        ordersEntity.setTotalPrice(orderPostDto.getTotalPrice());
        ordersEntity.setOrderProductses(orderPostDto.getOrderProductses());
        customersEntity.getOrderses().add(ordersEntity);
        entityManager.getTransaction().begin();
        entityManager.merge(ordersEntity);
        entityManager.merge(customersEntity);
        entityManager.getTransaction().commit();
        return ordersEntity;
    }

    @Override
    public OrdersEntity updateOrder(int OrderId, OrderPostDto orderPostDto) {
        OrdersEntity ordersEntity=entityManager.find(OrdersEntity.class, OrderId);
        ordersEntity.setCreatedAt(orderPostDto.getCreatedAt());
        ordersEntity.setIsSubmitted(orderPostDto.getIsSubmitted());
        ordersEntity.setTotalPrice(orderPostDto.getTotalPrice());
        ordersEntity.setOrderProductses(orderPostDto.getOrderProductses());
        entityManager.getTransaction().begin();
        entityManager.merge(ordersEntity);
        entityManager.getTransaction().commit();
        return ordersEntity;
    }

    @Override
    public OrdersEntity deleteOrder(int id) {
        OrdersEntity ordersEntity=entityManager.find(OrdersEntity.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(ordersEntity);
        entityManager.getTransaction().commit();
        return ordersEntity;
    }

}

