package org.com.rest.order.presentation;

import java.util.List;
import java.util.stream.Collectors;

import org.com.rest.order.repo.entity.OrderProductsEntity;
import org.com.rest.order.repo.entity.OrderProductsIdEntity;
import org.com.rest.order.repo.entity.OrdersEntity;
import org.com.rest.order.repo.impl.OrderRepoImp;
import org.com.rest.order.repo.impl.ShoppingCartRepoImp;
import org.com.rest.order.repo.interfaces.OrderRepo;
import org.com.rest.order.repo.interfaces.ShoppingCartRepo;
import org.com.rest.order.service.dto.OrderProductIdDto;
import org.com.rest.order.service.dto.OrderProductsDto;
import org.com.rest.product.repo.entity.ProductsEntity;
import org.com.rest.product.repo.impl.ProductRepoImp;
import org.com.rest.product.repo.interfaces.ProductRepo;

import org.com.rest.util.mapper.MapperClass;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("cart")
public class ShoppingCartController {

    @POST
    @Path("orderId/{orderId}/productId/{productId}/Quantity/{quantity}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addProductToShoppingCart(@PathParam("orderId") int orderId, @PathParam("productId") int productId,
            @PathParam("quantity") int quantity) {

        ProductRepo productRepo = new ProductRepoImp();
        ProductsEntity productsEntity = null;
        OrderRepo orders = new OrderRepoImp();
        OrdersEntity ordersEntity = null;

        try {
            orders = new OrderRepoImp();
            ordersEntity = orders.finfOrderById(orderId);
        } catch (Exception e) {
            return Response.ok().entity("There is not order found").build();
        }

        try {
            productRepo = new ProductRepoImp();
            productsEntity = productRepo.findProductById(productId);
        } catch (Exception e) {
            return Response.ok().entity("There is not product found found").build();
        }

        try {
            ShoppingCartRepo shoppingCartRepo = new ShoppingCartRepoImp();

            OrderProductsEntity orderProductsEntity = new OrderProductsEntity();
            orderProductsEntity.setId(new OrderProductsIdEntity(orderId, productId));
            orderProductsEntity.setProducts(productsEntity);
            orderProductsEntity.setOrders(ordersEntity);
            orderProductsEntity.setQuantity(quantity);
            orders.updateTotalPrice(orderId, productsEntity.getPrice() * quantity);
            productRepo.updateQuantity(productId, quantity);

            shoppingCartRepo.addProductToShoppingCart(orderProductsEntity);

        } catch (Exception e) {
            return Response.ok().entity("adding product to cart error").build();
        }

        return Response.ok().entity("Added successfully)").build();

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllShoppingCarts() {
        try {
            ShoppingCartRepo shoppingCartRepo = new ShoppingCartRepoImp();
            List<OrderProductsDto> orderProductsDtos = shoppingCartRepo.getCartProductsList().stream().map(
                    orderProductEntity -> {
                        OrderProductsDto orderProductsDto = MapperClass.OrderProductsEntityToDto(orderProductEntity);
                        return orderProductsDto;
                    }

            ).collect(Collectors.toList());

            shoppingCartRepo.getCartProductsList()
                    .forEach(orderProduct -> orderProductsDtos.add(MapperClass.OrderProductsEntityToDto(orderProduct)));
            return Response.ok().entity(orderProductsDtos).build();

        } catch (Exception e) {
            return Response.ok().entity("There is no carts").build();

        }
    }

    @GET
    @Path("customerId/{customerId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getCartByCustomerId(@PathParam("customerId") int id) {
        try {
            ShoppingCartRepo shoppingCartRepo = new ShoppingCartRepoImp();
            List<OrderProductsDto> orderProductsDtos = shoppingCartRepo.getCustomerShoppingCart(id).stream().map(
                    orderProductEntity -> {
                        OrderProductsDto orderProductsDto = MapperClass.OrderProductsEntityToDto(orderProductEntity);
                        return orderProductsDto;
                    }

            ).collect(Collectors.toList());

            shoppingCartRepo.getCartProductsList()
                    .forEach(orderProduct -> orderProductsDtos.add(MapperClass.OrderProductsEntityToDto(orderProduct)));
            return Response.ok().entity(orderProductsDtos).build();

        } catch (Exception e) {
            return Response.ok().entity("There is no carts").build();

        }
    }

    @PUT
    @Path("orderId/{orderId}/productId/{productId}/Quantity/{quantity}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateCartProduct(@PathParam("orderId") int orderId, @PathParam("productId") int productId,
            @PathParam("quantity") int quantity) {
        ProductRepo productRepo = new ProductRepoImp();
        ProductsEntity productsEntity = null;
        OrderRepo orders = new OrderRepoImp();
        OrdersEntity ordersEntity = null;

        try {
            ordersEntity = orders.finfOrderById(orderId);
        } catch (Exception e) {
            return Response.ok().entity("There is not order found").build();
        }

        ShoppingCartRepo shoppingCartRepo = new ShoppingCartRepoImp();
        shoppingCartRepo.updateProductInShoppingCart(productId, orderId, quantity);
        return Response.ok().entity("product updated)").build();
    }

    @DELETE
    @Path("orderId/{orderId}/productId/{productId}")
    public Response deleteOrder(@PathParam("orderId") int orderId, @PathParam("productId") int productId) {
        ProductRepo productRepo = new ProductRepoImp();
        ProductsEntity productsEntity = null;
        OrderRepo orders = new OrderRepoImp();
        OrdersEntity ordersEntity = null;

        try {
            ordersEntity = orders.finfOrderById(orderId);
        } catch (Exception e) {
            return Response.ok().entity("There is not order found").build();
        }

        try {
            productsEntity = productRepo.findProductById(productId);
        } catch (Exception e) {
            return Response.ok().entity("There is not product found found").build();
        }

        try {

            OrderProductIdDto orderProductIdDto = new OrderProductIdDto(orderId, productId);

            ShoppingCartRepo shoppingCartRepo = new ShoppingCartRepoImp();

            shoppingCartRepo.deleteProductFromShoppingCard(orderProductIdDto);

            return Response.ok().entity("Product Deleted from shoppingCart)").build();

        } catch (Exception e) {
            return Response.ok().entity("Product deleted from shoppingCart error").build();
        }
    }

}
