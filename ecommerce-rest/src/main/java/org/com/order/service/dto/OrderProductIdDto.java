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

    

    public OrderProductIdDto(int orderId2, int productId2) {
        this.orderId=orderId2;
        this.productId=productId2;
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
