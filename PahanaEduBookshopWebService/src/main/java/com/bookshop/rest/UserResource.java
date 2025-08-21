package com.bookshop.rest;

import com.bookshop.service.UserService;
import com.bookshop.model.LoginRequest;
import com.bookshop.model.RegisterRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")  // Path for user-related operations
public class UserResource {

    private UserService userService = new UserService();

    // User login endpoint (POST)
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        try {
            boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            if (isAuthenticated) {
                return Response.status(Response.Status.OK).entity("Login successful").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error during authentication").build();
        }
    }

    // User registration endpoint (POST)
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterRequest registerRequest) {
        try {
            userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
            return Response.status(Response.Status.CREATED).entity("User registered successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error during registration").build();
        }
    }
}
