package org.com.product.repo.interfaces;

import java.util.List;

import org.com.product.repo.entity.ProductsEntity;
import org.com.product.service.dto.ProductPostDto;

public interface ProductRepo {
    List<ProductsEntity> getAllProducts();
    List<ProductsEntity> searchProducts(String value);
    Long getProductsCount();
    ProductsEntity findProductById(Integer id);
    void addProduct(ProductPostDto productDto);
    ProductsEntity deleteProduct(int id);
    ProductsEntity updateProductById(int id,ProductPostDto productDto);
    List<ProductsEntity> findProductWithCategory(String category);
    boolean updateQuantity(int ProductId, int quantity);
    
    
}
