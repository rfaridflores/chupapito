package com.farid.ws.restws.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // serializa la clase para poder mapearla con la DB
public class Product {

	@Id // toda Entity DEBE TENER un Id, que es como la primary key
	private int id;
	private String name;
	private String description;
	private int price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
