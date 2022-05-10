package org.com.rest.util.mapper;

import org.com.rest.admin.repo.entity.AdminsEntity;
import org.com.rest.admin.service.dto.AdminDto;
import org.com.rest.admin.service.dto.AdminPostDto;
import org.com.rest.customer.repo.entity.CustomersEntity;
import org.com.rest.customer.service.dto.CustomersDto;
import org.com.rest.customer.service.dto.CustomersPostDto;
import org.com.rest.order.repo.entity.OrderProductsEntity;
import org.com.rest.order.repo.entity.OrdersEntity;
import org.com.rest.order.service.dto.OrderDto;
import org.com.rest.order.service.dto.OrderPostDto;
import org.com.rest.order.service.dto.OrderProductsDto;
import org.com.rest.product.repo.entity.ProductsEntity;
import org.com.rest.product.service.dto.ProductDto;
import org.com.rest.product.service.dto.ProductPostDto;

public class MapperClass {

    //Product Mapper
    
    public static ProductDto productEntityToDto(ProductsEntity productEntity){
        return new ProductDto(productEntity);
    }
    public static ProductsEntity productDtoToEntity(ProductDto productDto){
        return new ProductsEntity(productDto);

    }

    public static ProductsEntity productDtoToEntity(ProductPostDto productDtoPostDto){
        return new ProductsEntity(productDtoPostDto);

    }

    //Customer Mapper


    public static CustomersDto customerEntityToDto(CustomersEntity customersEntity){
        return new CustomersDto(customersEntity);
    }

    public static CustomersEntity customerDtoToEntity(CustomersDto  customersDto){
        return new CustomersEntity(customersDto);
    } 
    public static CustomersEntity customerDtoToEntity(CustomersPostDto  customersDto){
        return new CustomersEntity(customersDto);
    }  


     //Admin Mapper
     public static AdminDto AdminEntityToDto(AdminsEntity adminsEntity){
        return new AdminDto(adminsEntity);
    }

    public static AdminsEntity adminDtoToEntity(AdminDto  adminDto){
        return new AdminsEntity(adminDto);
    } 
    public static AdminsEntity adminDtoToEntity(AdminPostDto  adminDto){
        return new AdminsEntity(adminDto);
    }  

    //order
    public static OrderDto OrderEntityToDto(OrdersEntity ordersEntity){
        return new OrderDto(ordersEntity);
    }

    public static OrdersEntity OrderDtoToEntity(OrderDto orderDto){
        return new OrdersEntity(orderDto);
    }
    public static OrdersEntity OrderDtoToEntity(OrderPostDto orderDto){
        return new OrdersEntity(orderDto);
    }

    // Cart

    public static OrderProductsDto OrderProductsEntityToDto(OrderProductsEntity ordersEntity){
        return new OrderProductsDto(ordersEntity);
    }








    
}
