package com.farid.ws.soap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farid.ws.trainings.CreateOrdersRequest;
import com.farid.ws.trainings.CreateOrdersResponse;
import com.farid.ws.trainings.CustomerOrdersPortType;
import com.farid.ws.trainings.DeleteOrdersRequest;
import com.farid.ws.trainings.DeleteOrdersResponse;
import com.farid.ws.trainings.GetOrdersRequest;
import com.farid.ws.trainings.GetOrdersResponse;
import com.farid.ws.trainings.Order;
import com.farid.ws.trainings.Product;

public class CustomerOrdersWsImpl implements CustomerOrdersPortType {

	// creado constructor
	public CustomerOrdersWsImpl() {
		init();
	}

	// creando una db on the fly, customerOrders sera como una tabla en la db
	Map<BigInteger, List<Order>> customerOrders = new HashMap<>();
	int currentId;// sera como la 'primary key' de la db

	public void init() {
		List<Order> orders = new ArrayList<>();

		// Creando una orden
		Order order = new Order();
		order.setId(BigInteger.valueOf(1));

		// Creando un producto que ira en esa orden, puede ser mas de uno ya que el map
		// es para una lista de ordenes
		Product product = new Product();
		product.setId("1");
		product.setQuantity(BigInteger.valueOf(3));
		product.setDescription("This is a beautifull phone but expensive");

		// agregando el producto a la orden
		order.getProduct().add(product);
		// agregando el id de la orden
		orders.add(order);

		// populando el hashmap
		customerOrders.put(BigInteger.valueOf(++currentId), orders);
	}

	@Override
	public GetOrdersResponse getOrders(GetOrdersRequest request) {
		// lo primero es obtener el customer id
		BigInteger customerId = request.getCustomerId();
		List<Order> orders = customerOrders.get(customerId);

		// creando el response
		GetOrdersResponse response = new GetOrdersResponse();
		response.getOrder().addAll(orders);

		return response;
	}

	@Override
	public CreateOrdersResponse createOrders(CreateOrdersRequest request) {

		// taking customer id and order
		BigInteger customerId = request.getCustomerId();
		Order order = request.getOrder();

		// adding into a map (db)
		List<Order> orders = customerOrders.get(customerId);
		orders.add(order);

		// sending back in the response
		CreateOrdersResponse response = new CreateOrdersResponse();
		response.setResult(true);

		return null;
	}

	@Override
	public DeleteOrdersResponse deleteOrders(DeleteOrdersRequest request) {
		// taking customer id and order
		BigInteger orderId = request.getOrder().getId();
//		// removing into a map (db)
		customerOrders.remove(orderId);

		DeleteOrdersResponse deleteOrdersResponse = new DeleteOrdersResponse();
		deleteOrdersResponse.setResult(true);
		return deleteOrdersResponse;
	}
}
