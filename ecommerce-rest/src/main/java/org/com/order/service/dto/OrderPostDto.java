package org.com.order.service.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.com.order.repo.entity.OrderProductsEntity;
import org.com.order.repo.entity.OrdersEntity;

public class OrderPostDto {
    private Date createdAt;
    private Boolean isSubmitted;
    private double totalPrice;
    private Set<OrderProductsEntity> orderProductses = new HashSet<OrderProductsEntity>(0);

    public OrderPostDto() {
    }

    public OrderPostDto(OrdersEntity ordersEntity) {
        this.createdAt = ordersEntity.getCreatedAt();
        this.isSubmitted = ordersEntity.getIsSubmitted();
        this.totalPrice = ordersEntity.getTotalPrice();
        this.orderProductses = ordersEntity.getOrderProductses();
    }

   

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)throws java.text.ParseException {
        Date formattedDOB = new Date();
        formattedDOB = new SimpleDateFormat("yyyy-MM-dd").parse(createdAt);
        this.createdAt = formattedDOB;
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
