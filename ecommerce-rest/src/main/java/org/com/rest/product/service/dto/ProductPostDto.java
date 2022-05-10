package org.com.rest.product.service.dto;

import org.com.rest.product.repo.entity.ProductsEntity;

public class ProductPostDto {

    private String productName;
    private String productDesc;
    private double price;
    private int categoryId;
    private int stock;
    private String imgPath;


   public ProductPostDto() {
   }


   public ProductPostDto(ProductsEntity productEntity) {
       this.productName=productEntity.getProductName();
       this.productDesc=productEntity.getProductDesc();
       this.price=productEntity.getPrice(); 
       this.categoryId=productEntity.getCategoryId();
       this.imgPath=productEntity.getImgPath();
       this.stock=productEntity.getStock();
   }
   


   public String getProductName() {
       return productName;
   }


   public void setProductName(String productName) {
       this.productName = productName;
   }


   public String getProductDesc() {
       return productDesc;
   }


   public void setProductDesc(String productDesc) {
       this.productDesc = productDesc;
   }


   public double getPrice() {
       return price;
   }


   public void setPrice(double price) {
       this.price = price;
   }


   public int getCategoryId() {
       return categoryId;
   }


   public void setCategoryId(int categoryId) {
       this.categoryId = categoryId;
   }


   public int getStock() {
       return stock;
   }


   public void setStock(int stock) {
       this.stock = stock;
   }


   public String getImgPath() {
       return imgPath;
   }


   public void setImgPath(String imgPath) {
       this.imgPath = imgPath;
   }


   @Override
   public String toString() {
       return "ProductDto{" +
               ", productName='" + productName + '\'' +
               ", productDesc='" + productDesc + '\'' +
               ", price=" + price +
               ", category=" + categoryId +
               ", stock=" + stock +
               ", imagesPaths=" + imgPath +
               '}';
   }
   
    
}
