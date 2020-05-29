package com.farid.ws.soap.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.Features;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.WSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.farid.ws.soap.FileWsImpl;

@Configuration
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class WebServiceConfig {

	@Autowired // inject in run time
	private Bus bus;

	@Bean
	public Endpoint endpoint() {// EndPont debe de estar import de javax.xml.ws.Endpoint

		/*
		 * 1 parametro, bus es inyectado en run time 2 parametro, es la instancia de la
		 * clase que hemos definico como endPoint, en este caso HelloWs
		 */
		// CustomerOrdersWsImpl es nuestro endpoint

		EndpointImpl endpoint = new EndpointImpl(bus, new FileWsImpl());

		// webservice http://localhost:8080/wsdlfirstws/customerordersservice?wsdl
		// wsdlfirstws esta definido en application.properties (context)
		endpoint.publish("/fileWs");

		//We need to on mtom, que esta desabilitado por default, esto lo hacemos obteniendo el binding primero
		SOAPBinding binding = (SOAPBinding) endpoint.getBinding();
		binding.setMTOMEnabled(true);

		return endpoint;
	}
}
