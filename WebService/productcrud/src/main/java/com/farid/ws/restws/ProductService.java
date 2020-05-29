package com.farid.ws.restws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.farid.ws.restws.entity.Product;

@Consumes("/application/json")
@Produces("/application/json")
@Path("/productservice")
public interface ProductService {
	// listing the service that we want to expose

	@Path("/products")
	@GET
	List<Product> getProdcuts();

	@Path("/products/{id}")
	@GET
	Product getProduct(@PathParam("id") int id);

	@Path("/products")
	@POST()
	Response createProduct(Product product);

	@Path("/products")
	@PUT
	Response updateProduct(Product product);

	@Path("/products/{id}")
	@DELETE
	Response deleteProduct(int id);
}
