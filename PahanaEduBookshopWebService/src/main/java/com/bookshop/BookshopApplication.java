package com.bookshop;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class BookshopApplication extends Application {
    // This class activates JAX-RS for REST API and sets the base URL for all the APIs
}
