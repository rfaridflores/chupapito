package com.farid.ws.restws;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.farid.ws.restws.entity.Product;
import com.farid.ws.restws.repos.ProductRepository;

public class ProductServiceImpl implements ProductService {

	@Autowired // inject repo in runtime
	ProductRepository repository;

	@Override
	public List<Product> getProdcuts() {
		return repository.findAll();
	}

	@Override
	public Product getProduct(int id) {
		return repository.findById(id).get();
	}

	@Override
	public Response createProduct(Product product) {
		Product savedProduct = repository.save(product);
		return Response.ok(savedProduct).build();
	}

	@Override
	public Response updateProduct(Product product) {
		Product savedProduct = repository.save(product);//if object exist its been updated
		return Response.ok(savedProduct).build();
	}

	@Override
	public Response deleteProduct(int id) {
		ProductRepository productDeleted = repository;
		productDeleted.deleteById(id);
		
		return Response.ok(productDeleted).build();
	}

}
