package org.com.rest.customer.repo.entity;
// Generated May 3, 2022, 1:38:42 PM by Hibernate Tools 6.0.0.Beta2


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.com.rest.customer.service.dto.CustomersDto;
import org.com.rest.customer.service.dto.CustomersPostDto;
import org.com.rest.order.repo.entity.OrdersEntity;

/**
 * Customers generated by hbm2java
 */
@Entity
@Table(name="customers"
    ,catalog="myshop"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class CustomersEntity  implements java.io.Serializable {


     private int id;
     private String username;
     private String email;
     private String pass;
     private String interests;
     private Date dob;
     private Boolean isMale;
     private String job;
     private String address;
     private Set<OrdersEntity> orderses = new HashSet<OrdersEntity>(0);

    public CustomersEntity() {
    }

	
    public CustomersEntity(CustomersDto customersDto) {
       this.id = customersDto.getId();
       this.username = customersDto.getUsername();
       this.email = customersDto.getEmail();
       this.pass = customersDto.getPass();
       this.interests = customersDto.getInterests();
       this.dob = customersDto.getDob();
       this.isMale = customersDto.getIsMale();
       this.job = customersDto.getJob();
       this.address = customersDto.getAddress();
    //    this.orderses = orderses;
    }
    public CustomersEntity(CustomersPostDto customersDto) {
        this.username = customersDto.getUsername();
        this.email = customersDto.getEmail();
        this.pass = customersDto.getPass();
        this.interests = customersDto.getInterests();
        this.dob = customersDto.getDob();
        this.isMale = customersDto.getIsMale();
        this.job = customersDto.getJob();
        this.address = customersDto.getAddress();
     //    this.orderses = orderses;
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

    
    @Column(name="username", nullable=false, length=50)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="email", unique=true, nullable=false, length=254)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="pass", nullable=false, length=150)
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }

    
    @Column(name="interests", length=65535)
    public String getInterests() {
        return this.interests;
    }
    
    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dob", length=10)
    public Date getDob() {
        return this.dob;
    }
    
    public void setDob(Date dob) {
        this.dob = dob;
    }

    
    @Column(name="is_male")
    public Boolean getIsMale() {
        return this.isMale;
    }
    
    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }

    
    @Column(name="job", length=50)
    public String getJob() {
        return this.job;
    }
    
    public void setJob(String job) {
        this.job = job;
    }

    
    @Column(name="address", length=50)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customers")
    public Set<OrdersEntity> getOrderses() {
        return this.orderses;
    }
    
    public void setOrderses(Set<OrdersEntity> orderses) {
        this.orderses = orderses;
    }




}


