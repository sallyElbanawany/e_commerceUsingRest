package org.com.order.repo.interfaces;

import java.util.List;

import org.com.order.repo.entity.OrderProductsEntity;
import org.com.order.repo.entity.OrderProductsIdEntity;
import org.com.order.repo.entity.OrdersEntity;
import org.com.order.service.dto.OrderDto;
import org.com.order.service.dto.OrderProductIdDto;
import org.com.order.service.dto.OrderProductsDto;

public interface ShoppingCartRepo {

//        OrderDto getShoppingCart(Integer customerId);
    
//         boolean increaseProductInShoppingCart(Integer productId);
    
//         void decreaseProductInShoppingCart(Integer productId);
    
//         void deleteProductFromShoppingCard(Integer productId, Integer productQuantity);
    
//         List<OrdersEntity> addProductToShoppingCart(OrdersEntity orderEntity, Integer customerId);
        
        boolean addProductToShoppingCart(OrderProductsEntity orderProductsEntity);
        void deleteProductFromShoppingCard(OrderProductIdDto orderProductIdDto);
        boolean updateProductInShoppingCart(int productId, int userId, int quantity);
        List<OrderProductsEntity> getCustomerShoppingCart(Integer customerId);
        List<OrderProductsEntity> getCartProductsList();        







//     boolean chechIfProductExist(Integer userId, Integer productId);
    
}
