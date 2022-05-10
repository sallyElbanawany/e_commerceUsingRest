package org.com.rest.order.repo.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.com.rest.customer.repo.entity.CustomersEntity;
import org.com.rest.order.repo.entity.OrderProductsEntity;
import org.com.rest.order.repo.entity.OrderProductsIdEntity;
import org.com.rest.order.repo.entity.OrdersEntity;
import org.com.rest.order.repo.interfaces.ShoppingCartRepo;
import org.com.rest.order.service.dto.OrderDto;
import org.com.rest.order.service.dto.OrderPostDto;
import org.com.rest.order.service.dto.OrderProductIdDto;
import org.com.rest.order.service.dto.OrderProductsDto;
import org.com.rest.product.repo.entity.ProductsEntity;
import org.com.rest.product.repo.impl.ProductRepoImp;
import org.com.rest.product.repo.interfaces.ProductRepo;
import org.com.rest.util.factory.EntityManagerProvider;
import org.com.rest.util.mapper.MapperClass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class ShoppingCartRepoImp implements ShoppingCartRepo {
    EntityManagerProvider entityManagerProvider = EntityManagerProvider.getInstance();
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    @Override
    public boolean addProductToShoppingCart(OrderProductsEntity orderProductsEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(orderProductsEntity);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public void deleteProductFromShoppingCard(OrderProductIdDto orderProductsIdEntity) {
        
        Query query = entityManager.createQuery("SELECT p FROM OrderProductsEntity p where p.id.orderId=:orderId and p.id.productId=:productId");
        query.setParameter("orderId", orderProductsIdEntity.getOrderId());
        query.setParameter("productId", orderProductsIdEntity.getProductId());
        List<OrderProductsEntity> orderProductsEntities = query.getResultList();
        entityManager.getTransaction().begin();
        entityManager.remove(orderProductsEntities.get(0));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public boolean updateProductInShoppingCart(int productId, int orderId, int quantity) {
       


        Query query = entityManager.createQuery("SELECT p FROM OrderProductsEntity p where p.id.orderId=:orderId and p.id.productId=:productId");
        query.setParameter("orderId", orderId);
        query.setParameter("productId", productId);
        OrderProductsEntity updated= (OrderProductsEntity) query.getSingleResult();
        updated.setQuantity(quantity);
        entityManager.getTransaction().begin();
        entityManager.merge(updated);
        entityManager.getTransaction().commit();

        return true;
    }

    @Override
    public List<OrderProductsEntity> getCustomerShoppingCart(Integer customerId) {
        TypedQuery<OrdersEntity> orderQuery = entityManager
                .createQuery("select p from OrdersEntity p where p.customer_id like :param", OrdersEntity.class);
        orderQuery.setParameter("param", customerId);

        TypedQuery<OrderProductsEntity> query = entityManager
                .createQuery("from OrderProductsEntity op where op.OrdersEntity=:param ", OrderProductsEntity.class);
        query.setParameter("param", orderQuery);
        
        return query.getResultList();
    }

    @Override
    public List<OrderProductsEntity> getCartProductsList() {
        TypedQuery<OrderProductsEntity> query = entityManager.createQuery("from OrderProductsEntity",
                OrderProductsEntity.class);
        return query.getResultList();
    }

}
