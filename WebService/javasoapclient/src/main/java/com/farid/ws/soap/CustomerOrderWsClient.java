package com.farid.ws.soap;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import com.farid.ws.trainings.CustomerOrdersPortType;
import com.farid.ws.trainings.GetOrdersRequest;
import com.farid.ws.trainings.GetOrdersResponse;
import com.farid.ws.trainings.Order;

public class CustomerOrderWsClient {

	public static void main(String[] args) throws MalformedURLException {

		// aca utilizamos una java stub generated 'CustomerOrdersWsImplService' para
		// obtener la url del wsdl
		CustomerOrdersWsImplService service = new CustomerOrdersWsImplService(
				new URL("http://localhost:8080/wsdlfirstws/customerordersservice?wsdl"));

		// Accessando al portype ya que este wrap all the operations from service
		CustomerOrdersPortType customerOrdersWsImplPort = service.getCustomerOrdersWsImplPort();

		// ************************************************GetOrdersRequest********************************8
		// populando Request customer id
		GetOrdersRequest request = new GetOrdersRequest();
		request.setCustomerId(BigInteger.valueOf(1));

		// Obteniendo response
		GetOrdersResponse response = customerOrdersWsImplPort.getOrders(request);
		List<Order> order = response.getOrder();
		System.out.print("Number of orders for the customer are:" + order.size());
		System.out.print(String.format("Details customer are:  id='%s' product='%s'", order.get(0).getId(),
				order.get(0).getProduct().get(0).getDescription()));
	}
}
