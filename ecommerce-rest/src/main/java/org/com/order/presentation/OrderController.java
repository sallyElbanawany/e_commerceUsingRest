package org.com.order.presentation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.com.customer.repo.entity.CustomersEntity;
import org.com.customer.repo.impl.CustomerRepoImp;
import org.com.customer.repo.interfaces.CustomerRepo;
import org.com.customer.service.dto.CustomersDto;
import org.com.customer.service.dto.CustomersPostDto;
import org.com.order.repo.entity.OrdersEntity;
import org.com.order.repo.impl.OrderRepoImp;
import org.com.order.repo.interfaces.OrderRepo;
import org.com.order.service.dto.OrderDto;
import org.com.order.service.dto.OrderPostDto;
import org.com.util.mapper.MapperClass;

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
 
@Path("orders")
public class OrderController {


    @GET
    public Response getAllOrders() {
        try {
            OrderRepo orderRepo=new OrderRepoImp();
            List<OrderDto> orderDtos=orderRepo.getOrders().stream().map(orderEntity ->{
                OrderDto orderDto=MapperClass.OrderEntityToDto(orderEntity);
                return orderDto;
            }).collect(Collectors.toList());

            return Response.ok().entity(orderDtos).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("There is no orders found").build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByCustomerId(@PathParam("id") int id) {

        OrderRepo orderRepo=new OrderRepoImp();
        try {
            List<OrdersEntity> ordersEntity = orderRepo.getOrdersByCustomerId(id);
            List<OrderDto> orderDtos=orderRepo.getOrders().stream().map(orderEntity ->{
                OrderDto orderDto=MapperClass.OrderEntityToDto(orderEntity);
                return orderDto;
            }).collect(Collectors.toList());

            return Response.ok().entity(orderDtos).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("There is no orders found").build();
        }
    }



    // @GET
    // @Path("{id}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response getUnSubmittedOrder(@PathParam("id") int id) {

    //     OrderRepo orderRepo=new OrderRepoImp();
    //     List<OrderDto> orderDtos=null;
    //     try {
    //          orderDtos=orderRepo.getUnSubmittedOrder(id).stream().map(orderEntity->{
    //             OrderDto orderDto=MapperClass.OrderEntityToDto(orderEntity);
    //             return orderDto;
    //         }).collect(Collectors.toList());

    //         return Response.ok().entity(orderDtos).build();

    //     } catch (Exception e) {
    //         return Response.status(Response.Status.NOT_FOUND).entity(orderDtos).build();
    //     }
    // }

    

    // @POST
    // @Consumes(MediaType.APPLICATION_JSON)
    // public void addAdmin(OrderDto AdminsDto,@HeaderParam("Authorization") String authName) {
    //     if(authName.equalsIgnoreCase("admain") ){

    //     AdminRepo AdminRepo=new AdminRepoImp();
    //     AdminRepo.insertAdmin(AdminsDto);
    //     Response.ok().entity("Admin Added successfully").build();
    // } 
    // Response.status(Response.Status.FORBIDDEN)
    // .entity(" Not authorize").build();
    // }

    @POST
    @Path("customer/{id}/addOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(OrderPostDto orderPostDto,@PathParam("id") Integer id) {
        OrderRepo orderRepo=new OrderRepoImp();
        orderRepo.addOrder(id,orderPostDto);
        return Response.ok().entity("Order Added successfully").build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(OrderPostDto orderPostDto,@PathParam("id") Integer id,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") || authName.equalsIgnoreCase("clerk") ||authName.equalsIgnoreCase("customer") ){

        try {
            OrderRepo orderRepo=new OrderRepoImp();
            orderRepo.updateOrder(id,orderPostDto);
            return Response.ok().entity("Order updated").build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error happen on update order").build();
        }
    }
    return Response.status(Response.Status.FORBIDDEN)
    .entity("Can't delete Order not authorize").build();

    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOrder(@PathParam("id") int id,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") || authName.equalsIgnoreCase("clerk") ||authName.equalsIgnoreCase("customer") ){

            OrderRepo orderRepo=new OrderRepoImp();
            try {
            OrdersEntity customersEntity =  orderRepo.deleteOrder(id);
            OrderDto orderDto = MapperClass.OrderEntityToDto(customersEntity);

            return Response.ok().entity("order with id : "+ orderDto.getId()+"Deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Can't delete order with ID:" + id + " may be Not Found").build();
        }
    }
    return Response.status(Response.Status.FORBIDDEN)
    .entity("Can't delete Order with ID:" + id + "not authorize").build();

    }

    
}
