package org.com.rest.admin.repo.entity;
// Generated May 3, 2022, 1:38:42 PM by Hibernate Tools 6.0.0.Beta2


import org.com.rest.admin.service.dto.AdminDto;
import org.com.rest.admin.service.dto.AdminPostDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Admins generated by hbm2java
 */
@Entity
@Table(name="admins"
    ,catalog="myshop"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class AdminsEntity  implements java.io.Serializable {


     private int id;
     private String username;
     private String email;
     private String pass;
     private Boolean isAdmin;

    public AdminsEntity() {
    }

   
     public AdminsEntity(AdminDto adminDto) {
        this.id = adminDto.getId();
        this.username = adminDto.getUsername();
        this.email = adminDto.getEmail();
        this.pass = adminDto.getPass();
        this.isAdmin = adminDto.getIsAdmin();
    }


    public AdminsEntity(AdminPostDto adminDto) {
        this.username = adminDto.getUsername();
        this.email = adminDto.getEmail();
        this.pass = adminDto.getPass();
        this.isAdmin = adminDto.getIsAdmin();
    }


    @Id 

     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="username", nullable=false, length=50)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="email", unique=true, nullable=false, length=254)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="pass", nullable=false, length=150)
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }

    
    @Column(name="isAdmin")
    public Boolean getIsAdmin() {
        return this.isAdmin;
    }
    
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }




}


