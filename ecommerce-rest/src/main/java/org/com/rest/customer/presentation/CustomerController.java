package org.com.rest.customer.presentation;



import java.util.List;
import java.util.stream.Collectors;

import org.com.rest.customer.repo.entity.CustomersEntity;
import org.com.rest.customer.repo.impl.CustomerRepoImp;
import org.com.rest.customer.repo.interfaces.CustomerRepo;
import org.com.rest.customer.service.dto.CustomersDto;
import org.com.rest.customer.service.dto.CustomersPostDto;
import org.com.rest.order.repo.entity.OrdersEntity;
import org.com.rest.order.service.dto.OrderDto;
import org.com.rest.order.service.dto.OrderPostDto;
import org.com.rest.util.mapper.MapperClass;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("customer")
public class CustomerController {

    @GET
    public Response getAllCustomer() {
        try {
            CustomerRepo customerRepo=new CustomerRepoImp();
            List<CustomersDto> customerDto=customerRepo.getCustomers().stream().map(CustomerEntity ->{
                CustomersDto customersDto=MapperClass.customerEntityToDto(CustomerEntity);
                return customersDto;
            }).collect(Collectors.toList());

            return Response.ok().entity(customerDto).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("There is no Customer found").build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(CustomersPostDto customersDto) {
        CustomerRepo customerRepo=new CustomerRepoImp();
        customerRepo.insertCustomer(customersDto);
       return Response.ok().entity("Customer Added successfully").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {

        CustomerRepo customerRepo=new CustomerRepoImp();
        try {
            CustomersEntity customersEntity = customerRepo.findCustomerById(id);
            CustomersDto customersDto = MapperClass.customerEntityToDto(customersEntity);

            return Response.ok().entity(customersDto).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer with ID:" + id + " Not Found").build();
        }

    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") int id,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") || authName.equalsIgnoreCase("clerk")){

        CustomerRepo customerRepo=new CustomerRepoImp();
        try {
            CustomersEntity customersEntity = customerRepo.removeCustomer(id);
            CustomersDto customersDto = MapperClass.customerEntityToDto(customersEntity);

            return Response.ok().entity("Customer with id : "+ customersDto.getId()+"Deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Can't delete Customer with ID:" + id + " may be Not Found").build();
        }
    }
    return Response.status(Response.Status.FORBIDDEN)
    .entity("Can't delete Customer with ID:" + id + "not authorize").build();


    }

    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, CustomersPostDto customersPostDto,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") || authName.equalsIgnoreCase("clerk") || authName.equalsIgnoreCase("customer")){

        try {
            CustomerRepo customerRepo=new CustomerRepoImp();
            customerRepo.updateCustomer(id, customersPostDto);
            return Response.ok().entity("Customer updated").build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error happen on update Customer").build();
        }
    }
    return Response.status(Response.Status.NOT_FOUND)
                    .entity("Not authorize").build();
    }


    
}
