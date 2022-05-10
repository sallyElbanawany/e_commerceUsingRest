package org.com.rest.admin.service.dto;

import org.com.rest.admin.repo.entity.AdminsEntity;

public class AdminDto {

    private int id;
    private String username;
    private String email;
    private String pass;
    private Boolean isAdmin;
    public AdminDto() {
    }
    public AdminDto(AdminsEntity adminsEntity) {
        this.id = adminsEntity.getId();
        this.username = adminsEntity.getUsername();
        this.email = adminsEntity.getEmail();
        this.pass = adminsEntity.getPass();
        this.isAdmin = adminsEntity.getIsAdmin();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    

    

    
    
}
