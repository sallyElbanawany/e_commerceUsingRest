package org.com.rest.product.service.dto;


import java.util.ArrayList;
import java.util.List;

import org.com.rest.product.repo.entity.ProductsEntity;

import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


public class ProductDto {

     private int id;
     private String productName;
     private String productDesc;
     private double price;
     private int categoryId;
     private int stock;
     private String imgPath;

     @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
     private List<Link> links =new ArrayList<>();



    public ProductDto() {
    }


    public ProductDto(ProductsEntity productEntity) {
        this.productName=productEntity.getProductName();
        this.productDesc=productEntity.getProductDesc();
        this.price=productEntity.getPrice(); 
        this.categoryId=productEntity.getCategoryId();
        this.imgPath=productEntity.getImgPath();
        this.stock=productEntity.getStock();
        this.id=productEntity.getId();
    }

    
    
    


    public List<Link> getLinks() {
        return links;
    }


    public void setLinks(List<Link> links) {
        this.links = links;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
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

    // public void addLink(String uri,String rel)
    // {
    //     Link link=new Link();
    //     link.setUri(uri);
    //     link.setRel(rel);
    //     links.add(link);


        
    // }


    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", price=" + price +
                ", category=" + categoryId +
                ", stock=" + stock +
                ", imagesPaths=" + imgPath +
                '}';
    }
}
