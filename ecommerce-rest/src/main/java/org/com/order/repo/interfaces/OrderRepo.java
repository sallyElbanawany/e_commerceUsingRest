package org.com.order.repo.interfaces;

import java.util.List;

import org.com.order.repo.entity.OrdersEntity;
import org.com.order.service.dto.OrderPostDto;

public interface OrderRepo {
    List<OrdersEntity> getUnSubmittedOrder(int customerId);
    Long getOrdersCount();
    List<OrdersEntity> getOrders();
    List<OrdersEntity> getOrdersByCustomerId(int customerId);
    OrdersEntity finfOrderById(int orderId);
    OrdersEntity addOrder(int id,OrderPostDto orderPostDto);
    OrdersEntity updateOrder(int OrderId,OrderPostDto orderPostDto);
    OrdersEntity deleteOrder(int id);
    boolean updateTotalPrice(int orderId,double price);
}
