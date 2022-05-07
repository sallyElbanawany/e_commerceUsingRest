package org.com.order.presentation;

import org.com.order.repo.entity.OrdersEntity;
import org.com.order.repo.impl.ShoppingCartRepoImp;
import org.com.order.repo.interfaces.ShoppingCartRepo;
import org.com.order.service.dto.OrderPostDto;
import org.com.order.service.dto.OrderProductsDto;
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

@Path("cart")
public class ShoppingCartController {

    @POST
    @Path("customer/{id}/addProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProductToCart(OrderPostDto orderPostDto,@PathParam("id") Integer id) {
        OrdersEntity ordersEntity=MapperClass.OrderDtoToEntity(orderPostDto);
        ShoppingCartRepo shoppingCartRepo=new ShoppingCartRepoImp();
        shoppingCartRepo.addProductToShoppingCart(ordersEntity, id);
        return Response.ok().entity("product added successfully to cart").build();
    }
    
}
