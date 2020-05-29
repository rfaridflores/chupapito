package com.farid.ws.soap.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farid.ws.soap.HelloWs;

@Configuration
public class WebServiceConfig {
	
	@Autowired // inject in run time
	private Bus bus;
	
	@Bean
	public Endpoint endpoint() {// EndPont debe de estar impot de javax.xml.ws.Endpoint
		
		/*1 parametro, bus es inyectado en run time
		 * 2 parametro, es la instancia de la clase que hemos definico como endPoint, en este caso HelloWs
		 * */
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloWs());
		
		endpoint.publish("/hello");//webservice
		return endpoint;
	}

}
