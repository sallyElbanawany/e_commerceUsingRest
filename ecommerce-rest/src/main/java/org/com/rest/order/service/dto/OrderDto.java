package org.com.rest.order.service.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.com.rest.customer.repo.entity.CustomersEntity;
import org.com.rest.order.repo.entity.OrderProductsEntity;
import org.com.rest.order.repo.entity.OrdersEntity;

public class OrderDto {
    private int id;
    private CustomersEntity customers;
    private Date createdAt;
    private Boolean isSubmitted;
    private double totalPrice;
    private int customerId;
    private Set<OrderProductsEntity> orderProductses = new HashSet<OrderProductsEntity>(0);

    public OrderDto() {
    }

    public OrderDto(OrdersEntity ordersEntity) {
        this.id = ordersEntity.getId();
        this.createdAt = ordersEntity.getCreatedAt();
        this.isSubmitted = ordersEntity.getIsSubmitted();
        this.totalPrice = ordersEntity.getTotalPrice();
        this.orderProductses = ordersEntity.getOrderProductses();
        this.customerId=ordersEntity.getCustomers().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CustomersEntity getCustomers() {
        return customers;
    }

    public void setCustomers(CustomersEntity customers) {
        this.customers = customers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<OrderProductsEntity> getOrderProductses() {
        return orderProductses;
    }

    public void setOrderProductses(Set<OrderProductsEntity> orderProductses) {
        this.orderProductses = orderProductses;
    }

}
