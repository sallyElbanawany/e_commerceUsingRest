package org.com.order.repo.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.com.customer.repo.entity.CustomersEntity;
import org.com.order.repo.entity.OrderProductsEntity;
import org.com.order.repo.entity.OrdersEntity;
import org.com.order.repo.interfaces.ShoppingCartRepo;
import org.com.order.service.dto.OrderDto;
import org.com.order.service.dto.OrderPostDto;
import org.com.order.service.dto.OrderProductIdDto;
import org.com.order.service.dto.OrderProductsDto;
import org.com.product.repo.entity.ProductsEntity;
import org.com.product.repo.impl.ProductRepoImp;
import org.com.product.repo.interfaces.ProductRepo;
import org.com.util.factory.EntityManagerProvider;
import org.com.util.mapper.MapperClass;

import jakarta.persistence.EntityManager;

public class ShoppingCartRepoImp implements ShoppingCartRepo {
    EntityManagerProvider entityManagerProvider = EntityManagerProvider.getInstance();
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    @Override
    public OrderDto getShoppingCart(Integer customerId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean increaseProductInShoppingCart(Integer productId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void decreaseProductInShoppingCart(Integer productId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteProductFromShoppingCard(Integer productId, Integer productQuantity) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<OrdersEntity> addProductToShoppingCart(OrdersEntity orderEntity,
            Integer CustomerId) {
                

        List<OrdersEntity> ordersEntities  = new ArrayList<>();
        Set<OrderProductsEntity> orderProductEntities = new HashSet<>(orderEntity.getOrderProductses());
        orderEntity.setOrderProductses(null);
        CustomersEntity customerEntity = new CustomersEntity();
        customerEntity.setId(CustomerId);
        orderEntity.setCustomers(customerEntity);
        try {
            entityManager.getTransaction().begin();
            orderEntity = entityManager.merge(orderEntity);

            for (OrderProductsEntity orderProductEntity : orderProductEntities) {
                orderProductEntity.getId().setOrderId(orderEntity.getId());
                orderProductEntity.getId().setProductId(orderProductEntity.getId().getProductId());
                entityManager.merge(orderProductEntity);
            }
            entityManager.getTransaction().commit();
            orderEntity.setOrderProductses(orderProductEntities);
            ordersEntities.add(orderEntity);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ordersEntities;
    }

}
