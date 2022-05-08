package org.com.product.repo.impl;

import java.util.List;

import org.com.product.repo.entity.ProductsEntity;
import org.com.product.repo.interfaces.ProductRepo;
import org.com.product.service.dto.ProductPostDto;
import org.com.util.factory.EntityManagerProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ProductRepoImp implements ProductRepo {
    EntityManagerProvider entityManagerProvider = EntityManagerProvider.getInstance();
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    public List<ProductsEntity> getAllProducts() {
        return entityManager.createQuery("from ProductsEntity", ProductsEntity.class).getResultList();
    }

    public List<ProductsEntity> searchProducts(String value) {
        TypedQuery<ProductsEntity> query = entityManager
                .createQuery("select p from ProductsEntity p where p.productName like :param", ProductsEntity.class);
        query.setParameter("param", '%' + value + '%');
        return query.getResultList();
    }

    public Long getProductsCount() {
        // TODO Auto-generated method stub
        return null;
    }

    public ProductsEntity findProductById(Integer id) {
         return entityManager.find(ProductsEntity.class, id);
    }

    public void addProduct(ProductPostDto productDto) {

        ProductsEntity product = new ProductsEntity();
        product.setProductName(productDto.getProductName());
        product.setProductDesc(productDto.getProductDesc());
        product.setImgPath(productDto.getImgPath());
        product.setCategoryId(productDto.getCategoryId());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();

    }

    public ProductsEntity deleteProduct(int id) {
        ProductsEntity product = entityManager.find(ProductsEntity.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
        return product;
    }

    public ProductsEntity updateProductById(int id, ProductPostDto productDto) {
        ProductsEntity product = entityManager.find(ProductsEntity.class, id);
        product.setProductName(productDto.getProductName());
        product.setProductDesc(productDto.getProductDesc());
        product.setImgPath(productDto.getImgPath());
        product.setCategoryId(productDto.getCategoryId());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        return product;
    }

    @Override
    public List<ProductsEntity> findProductWithCategory(String category) {

        TypedQuery<ProductsEntity> query = null;
        switch (category) {
            case "mobile":
                query = entityManager
                        .createQuery("select p from ProductsEntity p where p.categoryId like :param",
                                ProductsEntity.class);
                query.setParameter("param", 1);
                break;
            case "laptop":
                query = entityManager
                        .createQuery("select p from ProductsEntity p where p.categoryId like :param",
                                ProductsEntity.class);
                query.setParameter("param", 2);

                break;

            default:
                break;
        }

        return query.getResultList();
    }

    @Override
    public boolean updateQuantity(int ProductId, int quantity) {
        
        ProductsEntity productsEntity= entityManager.find(ProductsEntity.class, ProductId);
        int currentQuantity=productsEntity.getStock();
        if(currentQuantity<quantity)
        return false;

        productsEntity.setStock(currentQuantity-quantity);
        entityManager.getTransaction().begin();
        entityManager.merge(productsEntity);
        entityManager.getTransaction().commit();

        return false;
    }

}
