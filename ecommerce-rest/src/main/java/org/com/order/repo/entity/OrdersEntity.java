package org.com.order.repo.entity;
// Generated May 3, 2022, 1:38:42 PM by Hibernate Tools 6.0.0.Beta2


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.com.customer.repo.entity.CustomersEntity;
import org.com.order.service.dto.OrderDto;
import org.com.order.service.dto.OrderPostDto;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name="orders"
    ,catalog="myshop"
)
public class OrdersEntity  implements java.io.Serializable {
    // @EmbeddedId
    // @AttributeOverrides({
    //         @AttributeOverride(name = "customer_id", column = @Column(name = "user_id", nullable = false)) })


     private int id;
     private CustomersEntity customers;
     private Date createdAt;
     private Boolean isSubmitted;
     private double totalPrice;
     private Set<OrderProductsEntity> orderProductses = new HashSet<OrderProductsEntity>(0);

    public OrdersEntity() {
    }

	
    public OrdersEntity(int id, CustomersEntity customers, Date createdAt, double totalPrice) {
        this.id = id;
        this.customers = customers;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
    }
    public OrdersEntity(int id, CustomersEntity customers, Date createdAt, Boolean isSubmitted, double totalPrice, Set<OrderProductsEntity> orderProductses) {
       this.id = id;
       this.customers = customers;
       this.createdAt = createdAt;
       this.isSubmitted = isSubmitted;
       this.totalPrice = totalPrice;
       this.orderProductses = orderProductses;
    }

    public OrdersEntity(OrderDto orderDto) {
        this.id = orderDto.getId();
        this.customers = orderDto.getCustomers();
        this.createdAt = orderDto.getCreatedAt();
        this.isSubmitted = orderDto.getIsSubmitted();
        this.totalPrice = orderDto.getTotalPrice();
        this.orderProductses = orderDto.getOrderProductses();
     }

     public OrdersEntity(OrderPostDto orderDto) {
        this.createdAt = orderDto.getCreatedAt();
        this.isSubmitted = orderDto.getIsSubmitted();
        this.totalPrice = orderDto.getTotalPrice();
        this.orderProductses = orderDto.getOrderProductses();
     }
   
     @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    public CustomersEntity getCustomers() {
        return this.customers;
    }
    
    public void setCustomers(CustomersEntity customers) {
        this.customers = customers;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="created_at", nullable=false, length=10)
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
    @Column(name="is_submitted")
    public Boolean getIsSubmitted() {
        return this.isSubmitted;
    }
    
    public void setIsSubmitted(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    
    @Column(name="total_price", nullable=false, precision=22, scale=0)
    public double getTotalPrice() {
        return this.totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    public Set<OrderProductsEntity> getOrderProductses() {
        return this.orderProductses;
    }
    
    public void setOrderProductses(Set<OrderProductsEntity> orderProductses) {
        this.orderProductses = orderProductses;
    }




}


