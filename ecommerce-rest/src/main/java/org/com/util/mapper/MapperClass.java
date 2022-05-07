package org.com.util.mapper;

import org.com.admin.repo.entity.AdminsEntity;
import org.com.admin.service.dto.AdminDto;
import org.com.admin.service.dto.AdminPostDto;
import org.com.customer.repo.entity.CustomersEntity;
import org.com.customer.service.dto.CustomersDto;
import org.com.customer.service.dto.CustomersPostDto;
import org.com.order.repo.entity.OrdersEntity;
import org.com.order.service.dto.OrderDto;
import org.com.order.service.dto.OrderPostDto;
import org.com.product.repo.entity.ProductsEntity;
import org.com.product.service.dto.ProductDto;
import org.com.product.service.dto.ProductPostDto;

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








    
}
