package com.farid.ws.restws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/productservice")
public interface ProducteService {

	@GET
	@Path("/products")
	List<Product> getProduct();

	@POST
	@Path("/products")
	long addProduct(Product product);
}
