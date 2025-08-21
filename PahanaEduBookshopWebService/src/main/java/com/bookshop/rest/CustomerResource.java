package com.bookshop.rest;

import com.bookshop.model.Customer;
import com.bookshop.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")  // Base URL path for customer-related endpoints
public class CustomerResource {

    private CustomerService customerService = new CustomerService();

    // Endpoint to add a new customer (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  // Accepts JSON request body
    @Produces(MediaType.APPLICATION_JSON)  // Sends JSON response
    public Response addCustomer(Customer customer) {
        try {
            customerService.addCustomer(customer);  // Call service to add the customer
            return Response.status(Response.Status.CREATED).entity(customer).build();  // Success response with the customer data
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding customer").build();  // Error response
        }
    }

    // Endpoint to retrieve a customer by account number (GET)
    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("accountNumber") int accountNumber) {
        try {
            Customer customer = (Customer) customerService.getCustomerById(accountNumber);  // Get customer by account number
            if (customer != null) {
                return Response.status(Response.Status.OK).entity(customer).build();  // Return customer data
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();  // If customer is not found
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving customer").build();  // Server error
        }
    }

    // Endpoint to retrieve all customers (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();  // Get all customers
            return Response.status(Response.Status.OK).entity(customers).build();  // Return list of customers
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving customers").build();  // Server error
        }
    }

    // Endpoint to update customer details (PUT)
    @PUT
    @Path("/{accountNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("accountNumber") int accountNumber, Customer updatedCustomer) {
        try {
            customerService.updateCustomer(accountNumber, updatedCustomer);  // Update customer details
            return Response.status(Response.Status.OK).entity(updatedCustomer).build();  // Return updated customer
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error updating customer").build();  // Error response
        }
    }

    // Endpoint to delete a customer (DELETE)
    @DELETE
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("accountNumber") int accountNumber) {
        try {
            customerService.deleteCustomer(accountNumber);  // Delete customer by account number
            return Response.status(Response.Status.NO_CONTENT).build();  // Success response
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error deleting customer").build();  // Error response
        }
    }
}
