package org.com.admin.presentation;
import java.util.List;
import java.util.stream.Collectors;

import org.com.admin.repo.entity.AdminsEntity;
import org.com.admin.repo.impl.AdminRepoImp;
import org.com.admin.repo.interfaces.AdminRepo;
import org.com.admin.service.dto.AdminDto;
import org.com.admin.service.dto.AdminPostDto;
import org.com.util.mapper.MapperClass;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("admin")
public class AdminController {

    @GET
    public Response getAllAdmin(@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") ){

        try {
            AdminRepo AdminRepo=new AdminRepoImp();
            List<AdminDto> AdminDto=AdminRepo.getAdmins().stream().map(AdminEntity ->{
                AdminDto AdminsDto=MapperClass.AdminEntityToDto(AdminEntity);
                return AdminsDto;
            }).collect(Collectors.toList());

            return Response.ok().entity(AdminDto).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("There is no Admin found").build();
        }
    } return Response.status(Response.Status.FORBIDDEN)
    .entity(" Not authorize").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAdmin(AdminPostDto AdminsDto,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") ){

        AdminRepo AdminRepo=new AdminRepoImp();
        AdminRepo.insertAdmin(AdminsDto);
        Response.ok().entity("Admin Added successfully").build();
    } 
    Response.status(Response.Status.FORBIDDEN)
    .entity(" Not authorize").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminById(@PathParam("id") int id,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") ){

        AdminRepo AdminRepo=new AdminRepoImp();
        try {
            AdminsEntity AdminsEntity = AdminRepo.findAdminById(id);
            AdminDto AdminsDto = MapperClass.AdminEntityToDto(AdminsEntity);

            return Response.ok().entity(AdminsDto).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Admin with ID:" + id + " Not Found").build();
        }
    } return Response.status(Response.Status.FORBIDDEN)
    .entity(" Not authorize").build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAdmin(@PathParam("id") int id,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") ){

        AdminRepo AdminRepo=new AdminRepoImp();
        try {
            AdminsEntity AdminsEntity = AdminRepo.removeAdmin(id);
            AdminDto AdminsDto = MapperClass.AdminEntityToDto(AdminsEntity);

            return Response.ok().entity("Admin with id : "+ AdminsDto.getId()+"Deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Can't delete Admin with ID:" + id + " may be Not Found").build();
        }
    } return Response.status(Response.Status.FORBIDDEN)
    .entity(" Not authorize").build();

    }

    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAdmin(@PathParam("id") int id, AdminPostDto AdminsPostDto,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") ){

        try {
            AdminRepo AdminRepo=new AdminRepoImp();
            AdminRepo.updateAdmin(id, AdminsPostDto);
            return Response.ok().entity("Admin updated").build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error happen on update Admin").build();
        }
    }
    return Response.status(Response.Status.FORBIDDEN)
    .entity(" Not authorize").build();

    }
    @GET
    @Path("AdminsOrClerks/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdminsOrClerks(@PathParam("param") String params,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") ){

        AdminRepo AdminRepo=new AdminRepoImp();
        try {
            List<AdminDto> founded = AdminRepo.getOnlyAdminsOrOnlyClerk(params).stream()
                    .map(adminEntity -> {
                        AdminDto adminDto = MapperClass.AdminEntityToDto(adminEntity);
                        return adminDto;
                    }).collect(Collectors.toList());

            return Response.ok().entity(founded).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(" Not Found").build();
        }
    }
    return Response.status(Response.Status.FORBIDDEN)
                    .entity(" Not authorize").build();
    }

    
}
