package org.com.product.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.com.product.repo.entity.ProductsEntity;
import org.com.product.repo.impl.ProductRepoImp;
import org.com.product.repo.interfaces.ProductRepo;
import org.com.product.service.dto.ProductDto;
import org.com.product.service.dto.ProductPostDto;
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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("products")
public class ProductController {
    private List<Link> links = new ArrayList<>();

    @GET
    public Response getAllProducts(@Context UriInfo uriInfo) {
        try {
            ProductRepo productRepo = new ProductRepoImp();
            List<ProductDto> products = productRepo.getAllProducts().stream().map(productEntity -> {
                ProductDto productDto = MapperClass.productEntityToDto(productEntity);
                allLinks(productDto,uriInfo);
                return productDto;
            }).collect(Collectors.toList());

            return Response.ok().entity(products).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("There is no products found").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(ProductPostDto productDto ,@HeaderParam("Authorization") String authName) {
        if(authName.equalsIgnoreCase("admain") || authName.equalsIgnoreCase("clerk")){
    
              try {
                ProductRepo productRepo = new ProductRepoImp();
                productRepo.addProduct(productDto);
                Response.ok().entity("Product Added successfully").build();
              } catch (Exception e) {
                Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error in insert product").build();
            }
        }
        
             Response.status(Response.Status.FORBIDDEN).entity("You aren't authorize to insert product").build();

        
       
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id, @Context UriInfo uriInfo) {

        ProductRepo productRepo = new ProductRepoImp();
        try {
            ProductsEntity product = productRepo.findProductById(id);
            ProductDto productDto = MapperClass.productEntityToDto(product);
            // String
            // uri=uriInfo.getBaseUri(Builder.Path(ProductController.class).Path(Integer.toString(productDto.getId())).build().toString());
            // productDto.addLink(uri, "self");
            allLinksforOnlyProduct( productDto ,uriInfo);

            return Response.ok().entity(productDto).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("product with ID:" + id + " Not Found").build();
        }

        // ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "Probably
        // Wrong ID");
        // Response response =
        // Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
        // throw new WebApplicationException(response);
        // throw new ResourceNotFoundException("product with ID:" + id + " Not Found");
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id,@HeaderParam("Authorization") String authName) {

        ProductRepo productRepo = new ProductRepoImp();
       
            if(authName.equalsIgnoreCase("admain") || authName.equalsIgnoreCase("clerk")){
                try {
                    ProductsEntity product = productRepo.deleteProduct(id);
                    ProductDto productDto = MapperClass.productEntityToDto(product);
        
                    return Response.ok().entity("Product with id : "+ productDto.getId()+"Deleted successfully").build();
                
                   
                } catch (Exception e) {
                    return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error on delete this product may be not found Not Found").build();     
                           }
                        }
                        
           
                return Response.status(Response.Status.NOT_FOUND)
                .entity("not authorize").build();  

    }

    @GET
    @Path("search")
    public Response searchProducts(@QueryParam("pattern") String pattern,@Context UriInfo uriInfo) {
        ProductRepo productRepo = new ProductRepoImp();
        try {
            List<ProductDto> foundedProducts = productRepo.searchProducts(pattern).stream().map(productEntity -> {
                ProductDto productDto = MapperClass.productEntityToDto(productEntity);
                allLinks(productDto, uriInfo);
                return productDto;
            }).collect(Collectors.toList());

            return Response.ok().entity(foundedProducts).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product Not Found").build();
        }

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, ProductPostDto productDto,@HeaderParam("Authorization") String authName) {
            if(authName.equalsIgnoreCase("admain") || authName.equalsIgnoreCase("clerk")){
                   try {
                    ProductRepo productRepo = new ProductRepoImp();
                    productRepo.updateProductById(id, productDto);
                    return Response.ok().entity("product updated").build();

                   } catch (Exception e) {
                    return Response.status(Response.Status.NOT_FOUND)
                    .entity("Error on update this product may be not found Not Found").build();     
                                        }

                } 
                return Response.ok().entity("not authorize").build();

 
    }

    @GET
    @Path("category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductWithCategory(@PathParam("category") String category) {
        ProductRepo productRepo = new ProductRepoImp();
        try {
            List<ProductDto> foundedProducts = productRepo.findProductWithCategory(category).stream()
                    .map(productEntity -> {
                        ProductDto productDto = MapperClass.productEntityToDto(productEntity);
                        return productDto;
                    }).collect(Collectors.toList());

            return Response.ok().entity(foundedProducts).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Product Not Found").build();
        }
    }

    public void allLinks(ProductDto dto, @Context UriInfo uriInfo) {
        Link self = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path(ProductController.class)
                .path(Integer.toString((dto.getId())))).rel("self").build();
        links.add(self);
        dto.setLinks(links);
    }
    public void allLinksforOnlyProduct(ProductDto productDto, @Context UriInfo uriInfo) {
        Link prev = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path(ProductController.class)
                .path(Integer.toString((productDto.getId()) - 1))).rel("prev").build();
        Link next = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path(ProductController.class)
                .path(Integer.toString((productDto.getId()) + 1))).rel("next").build();
        Link self = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path(ProductController.class)
                .path(Integer.toString((productDto.getId())))).rel("self").build();
        Link delete = Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path(ProductController.class)
                .path(Integer.toString((productDto.getId())))).rel("delete").type("DELETE").build();

        links.add(prev);
        links.add(next);
        links.add(self);
        links.add(delete);
        productDto.setLinks(links);
    }


}
