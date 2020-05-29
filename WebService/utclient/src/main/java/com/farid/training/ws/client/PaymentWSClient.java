package com.farid.training.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;

import com.bharath.ws.soap.PaymentProcessor;
import com.bharath.ws.soap.PaymentProcessorRequest;
import com.bharath.ws.soap.PaymentProcessorResponse;
import com.bharath.ws.soap.PaymentProcessor_Service;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.apache.cxf.endpoint.Client;

public class PaymentWSClient {

	public static void main(String[] args) {
		// Creating instance of PaymentProcessor_Service
		try {
			PaymentProcessor_Service service = new PaymentProcessor_Service(
					new URL("http://localhost:8080/javafirstws/paymentProcessor?wsdl"));

			// get port service
			PaymentProcessor port = service.getPaymentProcessorImplPort();
//			PaymentProcessorResponse response = port.processPayment(new PaymentProcessorRequest());
//			System.out.println(response);

			// to avoid error 'A security error was encountered when verifying the message'
			// we need to:
			// Access to client object
			Client client = ClientProxy.getClient(port);
			Endpoint endpoint = client.getEndpoint();

			// Configuring Properties, Creating user name and password type to use
			// (credentials)
			Map<String, Object> props = new HashMap<String, Object>();
			props.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			props.put(WSHandlerConstants.USER, "cxf");
			props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
			// configurando el Callback para security issues
			props.put(WSHandlerConstants.PW_CALLBACK_CLASS, UTPasswordCallback.class.getName());

			// configuring interceptor
			// Interceptor interceptara el mensaje entrante
			WSS4JInInterceptor wssIn = new WSS4JInInterceptor(props);// esta es una dependecia en el pom file del plugin
																		// cxf-rt-ws-security
			endpoint.getOutInterceptors().add(wssIn);

			PaymentProcessorResponse response = port.processPayment(new PaymentProcessorRequest());
			System.out.println(response);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
