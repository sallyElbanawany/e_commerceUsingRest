package org.com.order.service.dto;

import org.com.order.repo.entity.OrderProductsIdEntity;

public class OrderProductIdDto {
    private int orderId;
    private int productId;

    public OrderProductIdDto() {
    }

    public OrderProductIdDto(OrderProductsIdEntity orderProductsIdEntity) {
        this.orderId = orderProductsIdEntity.getOrderId();
        this.productId = orderProductsIdEntity.getProductId();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
