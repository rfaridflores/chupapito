package com.farid.ws.restws.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farid.ws.restws.entity.Product;

//JpaRepository<T, ID>  T=Product y ID, es de tipo entero, 
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
