package org.com.rest.admin.repo.impl;

import java.util.List;

import org.com.rest.admin.repo.entity.AdminsEntity;
import org.com.rest.admin.repo.interfaces.AdminRepo;
import org.com.rest.admin.service.dto.AdminPostDto;
import org.com.rest.util.factory.EntityManagerProvider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AdminRepoImp implements AdminRepo{
    EntityManagerProvider entityManagerProvider = EntityManagerProvider.getInstance();
    EntityManager entityManager = entityManagerProvider.getEntityManager();

    @Override
    public AdminsEntity findAdminById(Integer id) {
        return entityManager.find(AdminsEntity.class, id);

    }

    @Override
    public List<AdminsEntity> getAdmins() {
        return entityManager.createQuery("from AdminsEntity", AdminsEntity.class).getResultList();

    }

    @Override
    public void insertAdmin(AdminPostDto adminPostDto) {
        AdminsEntity adminsEntity=new AdminsEntity();
        adminsEntity.setUsername(adminPostDto.getUsername());
        adminsEntity.setEmail(adminPostDto.getEmail());
        adminsEntity.setPass(adminPostDto.getPass());
        adminsEntity.setIsAdmin(adminPostDto.getIsAdmin());    
        entityManager.getTransaction().begin();
        entityManager.merge(adminsEntity);
        entityManager.getTransaction().commit();  
    }

    @Override
    public AdminsEntity updateAdmin(int id, AdminPostDto adminPostDto) {
        AdminsEntity adminsEntity=entityManager.find(AdminsEntity.class, id);
        adminsEntity.setUsername(adminPostDto.getUsername());
        adminsEntity.setEmail(adminPostDto.getEmail());
        adminsEntity.setPass(adminPostDto.getPass());
        adminsEntity.setIsAdmin(adminPostDto.getIsAdmin());    
        entityManager.getTransaction().begin();
        entityManager.merge(adminsEntity);
        entityManager.getTransaction().commit();  
        return adminsEntity;
    }

    @Override
    public AdminsEntity removeAdmin(int id) {
        AdminsEntity adminsEntity=entityManager.find(AdminsEntity.class, id); 
        entityManager.getTransaction().begin();
        entityManager.remove(adminsEntity);
        entityManager.getTransaction().commit();  
        return adminsEntity;
    }

    @Override
    public Long getAdminCount() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AdminsEntity> getOnlyAdminsOrOnlyClerk(String params) {

          TypedQuery<AdminsEntity> query = null;
        switch (params) {
            case "onlyAdmins":
                query = entityManager
                        .createQuery("select p from AdminsEntity p where p.isAdmin = :param",
                                AdminsEntity.class);
                query.setParameter("param", true);
                break;
            case "onlyClerks":
                query = entityManager
                        .createQuery("select p from AdminsEntity p where p.isAdmin = :param",
                        AdminsEntity.class);
                query.setParameter("param", false);

                break;

            default:
                break;
        }

        return query.getResultList();
    }

    
}
