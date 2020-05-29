package com.farid.ws.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.cxf.feature.Features;

@WebService // se agreaga esta anotacion para convertir la clase en EndPoint
@Features(features="org.apache.cxf.feature.LoggingFeature")// con esto estamos imprimiendo en consola el request y response 
//del servicio, es decir los mesajes de SOAP en consola

public class HelloWs {

	@WebMethod // se agrega este metodo para convertirlo en ednpoint, este es el nombre del servicio 
	public String hello() {
		return "Hello";
	}
}
