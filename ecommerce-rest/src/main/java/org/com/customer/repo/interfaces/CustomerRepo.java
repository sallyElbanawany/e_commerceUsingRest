package org.com.customer.repo.interfaces;

import java.util.List;

import org.com.customer.repo.entity.CustomersEntity;
import org.com.customer.service.dto.CustomersPostDto;
import org.com.order.repo.entity.OrdersEntity;
import org.com.order.service.dto.OrderDto;
import org.com.order.service.dto.OrderPostDto;

public interface CustomerRepo {
    CustomersEntity findCustomerById(Integer id);
    List<CustomersEntity> getCustomers();
    void insertCustomer(CustomersPostDto customerPostDto);
    CustomersEntity updateCustomer(int id , CustomersPostDto customerPostDto);
    CustomersEntity removeCustomer(int id);
    



    Long getCustomerCount();

    
}
