package com.bookshop.rest;

import com.bookshop.model.Item;
import com.bookshop.service.ItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/items")  // Path for item-related operations
public class ItemResource {

    private ItemService itemService = new ItemService();

    // Add a new item (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) {
        try {
            itemService.addItem(item);
            return Response.status(Response.Status.CREATED).entity(item).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding item").build();
        }
    }

    // Retrieve an item by ID (GET)
    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("itemId") int itemId) {
        try {
            Item item = (Item) itemService.getItemById(itemId);
            if (item != null) {
                return Response.status(Response.Status.OK).entity(item).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Item not found").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving item").build();
        }
    }

    // Retrieve all items (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        try {
            List<Item> items = itemService.getAllItems();
            return Response.status(Response.Status.OK).entity(items).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving items").build();
        }
    }

    // Update an item by ID (PUT)
    @PUT
    @Path("/{itemId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("itemId") int itemId, Item updatedItem) {
        try {
            itemService.updateItem(itemId, updatedItem);
            return Response.status(Response.Status.OK).entity(updatedItem).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error updating item").build();
        }
    }

    // Delete an item by ID (DELETE)
    @DELETE
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("itemId") int itemId) {
        try {
            itemService.deleteItem(itemId);
            return Response.status(Response.Status.NO_CONTENT).build();  // 204 No Content
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error deleting item").build();
        }
    }
}
