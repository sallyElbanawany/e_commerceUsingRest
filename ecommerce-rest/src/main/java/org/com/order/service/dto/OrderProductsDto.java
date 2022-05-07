package org.com.order.service.dto;

import org.com.order.repo.entity.OrderProductsEntity;
import org.com.order.repo.entity.OrderProductsIdEntity;
import org.com.order.repo.entity.OrdersEntity;
import org.com.product.repo.entity.ProductsEntity;

public class OrderProductsDto {

    private OrderProductsIdEntity id;
    private ProductsEntity products;
    private OrdersEntity orders;
    private int quantity;

    public OrderProductsDto() {
    }

    public OrderProductsDto(OrderProductsEntity orderProductsEntity) {
        this.id = orderProductsEntity.getId();
        this.products = orderProductsEntity.getProducts();
        this.orders = orderProductsEntity.getOrders();
        this.quantity = orderProductsEntity.getQuantity();
    }

    public OrderProductsIdEntity getId() {
        return id;
    }

    public void setId(OrderProductsIdEntity id) {
        this.id = id;
    }

    public ProductsEntity getProducts() {
        return products;
    }

    public void setProducts(ProductsEntity products) {
        this.products = products;
    }

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
