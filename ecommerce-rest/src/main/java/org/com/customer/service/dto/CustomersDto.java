package org.com.customer.service.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.com.customer.repo.entity.CustomersEntity;
import org.com.order.repo.entity.OrdersEntity;

public class CustomersDto {
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

    public CustomersDto() {
    }

    public CustomersDto(CustomersEntity customersEntity) {
        this.id = customersEntity.getId();
        this.username = customersEntity.getUsername();
        this.email = customersEntity.getEmail();
        this.pass = customersEntity.getPass();
        this.interests = customersEntity.getInterests();
        this.dob = customersEntity.getDob();
        this.isMale = customersEntity.getIsMale();
        this.job = customersEntity.getJob();
        this.address = customersEntity.getAddress();
        this.orderses = customersEntity.getOrderses();
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Boolean getIsMale() {
        return isMale;
    }

    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<OrdersEntity> getOrderses() {
        return orderses;
    }

    public void setOrderses(Set<OrdersEntity> orderses) {
        this.orderses = orderses;
    }

}
