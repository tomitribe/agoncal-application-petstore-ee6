package org.agoncal.application.petstore.rest;

import org.agoncal.application.petstore.domain.Category;
import org.agoncal.application.petstore.domain.Item;
import org.agoncal.application.petstore.domain.Product;
import org.agoncal.application.petstore.meta.Id;
import org.agoncal.application.petstore.meta.StandardConsumes;
import org.agoncal.application.petstore.meta.StandardProduces;
import org.agoncal.application.petstore.service.CatalogService;
import org.agoncal.application.petstore.util.Loggable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Stateless
@Loggable
@Path("/catalog")
public class CatalogRestService implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private CatalogService catalogService;

    @Context
    private UriInfo uriInfo;

    // ======================================
    // =          Business methods          =
    // ======================================

    @GET
    @Path("/categories")
    @StandardProduces
    public List<Category> findAllCategories() {
        return catalogService.findAllCategories();
    }

    @GET
    @Path("/category/{id}")
    @StandardProduces
    public Category findCategory(@Id Long categoryId) {
        return catalogService.findCategory(categoryId);
    }

    @POST
    @Path("/category")
    @StandardConsumes
    public Response createCategory(JAXBElement<Category> xmlCategory) {
        Category category = catalogService.createCategory(xmlCategory.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(category.getId().toString()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/category")
    @StandardConsumes
    public Response updateCategory(JAXBElement<Category> xmlCategory) {
        Category category = catalogService.updateCategory(xmlCategory.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(category.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("/category/{id}")
    public Response removeCategory(@Id Long categoryId) {
        catalogService.removeCategory(categoryId);
        return Response.noContent().build();
    }

    @GET
    @Path("/products")
    @StandardProduces
    public List<Product> findAllProducts() {
        return catalogService.findAllProducts();
    }

    @GET
    @Path("/product/{id}")
    @StandardProduces
    public Product findProduct(@Id Long productId) {
        return catalogService.findProduct(productId);
    }

    @POST
    @Path("/product")
    @StandardConsumes
    public Response createProduct(JAXBElement<Product> xmlProduct) {
        Product product = catalogService.createProduct(xmlProduct.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(product.getId().toString()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/product")
    @StandardConsumes
    public Response updateProduct(JAXBElement<Product> xmlProduct) {
        Product product = catalogService.updateProduct(xmlProduct.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(product.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("/product/{id}")
    public Response removeProduct(@Id Long productId) {
        catalogService.removeProduct(productId);
        return Response.noContent().build();
    }

    @GET
    @Path("/items")
    @StandardProduces
    public List<Item> findAllItems() {
        return catalogService.findAllItems();
    }

    @GET
    @Path("/item/{id}")
    @StandardProduces
    public Item findItem(@Id Long itemId) {
        return catalogService.findItem(itemId);
    }

    @POST
    @Path("/item")
    @StandardConsumes
    public Response createItem(JAXBElement<Item> xmlItem) {
        Item item = catalogService.createItem(xmlItem.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(item.getId().toString()).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("/item")
    @StandardConsumes
    public Response updateItem(JAXBElement<Item> xmlItem) {
        Item item = catalogService.updateItem(xmlItem.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(item.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @DELETE
    @Path("/item/{id}")
    public Response removeItem(@Id Long itemId) {
        catalogService.removeItem(itemId);
        return Response.noContent().build();
    }

}
