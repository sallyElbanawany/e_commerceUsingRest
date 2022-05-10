package org.com.rest.customer.repo.interfaces;

import java.util.List;

import org.com.rest.customer.repo.entity.CustomersEntity;
import org.com.rest.customer.service.dto.CustomersPostDto;
import org.com.rest.order.repo.entity.OrdersEntity;
import org.com.rest.order.service.dto.OrderDto;
import org.com.rest.order.service.dto.OrderPostDto;

public interface CustomerRepo {
    CustomersEntity findCustomerById(Integer id);
    List<CustomersEntity> getCustomers();
    void insertCustomer(CustomersPostDto customerPostDto);
    CustomersEntity updateCustomer(int id , CustomersPostDto customerPostDto);
    CustomersEntity removeCustomer(int id);
    



    Long getCustomerCount();

    
}
