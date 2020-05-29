package com.farid.ws.soap.config;

import java.util.ArrayList;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.Features;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farid.ws.handlers.SiteHandler;
import com.farid.ws.soap.CustomerOrdersWsImpl;


@Configuration
@Features(features="org.apache.cxf.feature.LoggingFeature")
public class WebServiceConfig {
	
	@Autowired // inject in run time
	private Bus bus;
	
	@Bean
	public Endpoint endpoint() {// EndPont debe de estar import de javax.xml.ws.Endpoint
		
		/*1 parametro, bus es inyectado en run time
		 * 2 parametro, es la instancia de la clase que hemos definico como endPoint, en este caso HelloWs
		 * */
		//CustomerOrdersWsImpl es nuestro endpoint
		
		EndpointImpl endpoint = new EndpointImpl(bus, new CustomerOrdersWsImpl());
		
		//webservice http://localhost:8080/wsdlfirstws/customerordersservice?wsdl
		//wsdlfirstws esta definido en application.properties (context)
		endpoint.publish("/customerordersservice");
		
		//configurar the handler, este paso es necesario solo cuando necesitamos musar JAX-WS handlers
		SOAPBinding binding= (SOAPBinding) endpoint.getBinding();
		ArrayList<Handler> handlerChain = new ArrayList<>();
		handlerChain.add(new SiteHandler());
		binding.setHandlerChain(handlerChain);
		
		
		return endpoint;
	}
}
