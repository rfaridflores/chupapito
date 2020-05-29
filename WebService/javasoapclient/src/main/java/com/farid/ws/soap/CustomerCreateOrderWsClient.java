package com.farid.ws.soap;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.cxf.feature.Features;

import com.farid.ws.trainings.CreateOrdersRequest;
import com.farid.ws.trainings.CreateOrdersResponse;
import com.farid.ws.trainings.CustomerOrdersPortType;
import com.farid.ws.trainings.GetOrdersRequest;
import com.farid.ws.trainings.GetOrdersResponse;
import com.farid.ws.trainings.Order;
import com.farid.ws.trainings.Product;
@Features(features="org.apache.cxf.feature.LoggingFeature")

public class CustomerCreateOrderWsClient {

	public static void main(String[] args) throws MalformedURLException {

		// aca utilizamos una java stub generated 'CustomerOrdersWsImplService' para
		// obtener la url del wsdl
		CustomerOrdersWsImplService service = new CustomerOrdersWsImplService(
				new URL("http://localhost:8080/wsdlfirstws/customerordersservice?wsdl"));

		// Accessando al portype ya que este wrap all the operations from service
		CustomerOrdersPortType customerOrdersWsImplPort = service.getCustomerOrdersWsImplPort();

		// ************************************************CreatetOrdersRequest********************************

		CreateOrdersRequest ordersRequest = new CreateOrdersRequest();
		ordersRequest.setCustomerId(BigInteger.valueOf(1));

		Order order = new Order();
		order.setId(BigInteger.valueOf(2));

		Product product = new Product();
		product.setId("6");
		product.setDescription("Client test a ver si funciona");
		product.setQuantity(BigInteger.valueOf(10));
		order.getProduct().add(product);
		
		ordersRequest.setOrder(order);

		System.out.print("****************************   "+ordersRequest.toString());
		CreateOrdersResponse response2 = customerOrdersWsImplPort.createOrders(ordersRequest);
		response2.setResult(true);

	}

}
