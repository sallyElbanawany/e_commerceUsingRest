package org.com.admin.repo.interfaces;

import java.util.List;

import org.com.admin.repo.entity.AdminsEntity;
import org.com.admin.service.dto.AdminPostDto;

public interface AdminRepo {
    AdminsEntity findAdminById(Integer id);
    List<AdminsEntity> getAdmins();
    List<AdminsEntity> getOnlyAdminsOrOnlyClerk(String onlyAdmins);
    void insertAdmin(AdminPostDto adminPostDto);
    AdminsEntity updateAdmin(int id , AdminPostDto adminPostDto);
    AdminsEntity removeAdmin(int id);


    Long getAdminCount();
    
}
