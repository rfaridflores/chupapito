package com.farid.ws.restws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.farid.ws.restws.model.Passenger;

@Path("/passengerservice")
@Produces("application/xml,application/json")
// el parametro application/x-www-form-urlencoded sirve para habilitar en el vio de forms en la url @FormParam
@Consumes("application/xml,application/x-www-form-urlencoded")
public interface PassengerService {

	@Path("/passengers")
	@GET
	List<Passenger> getPassenger(@QueryParam("start") int start, @QueryParam("size") int size);

	@Path("/passengers")
	@POST
	// Passenger addPassenger(Passenger passenger);
	// To submit Form paramterers in a request , we use: @FormParam
	// @HeaderParam leemos un header,nos sirve para identificar la agencia de donde proviene el request
	// HttpHeaders jax-rs leemos todos los headers que vienene en el request de un solo
	// @Context inject all http header to a particular object, header for our example
	void addPassenger(@FormParam("fistName") String fistName, @FormParam("lastName") String lastName,
			@HeaderParam("agent") String agent, @Context HttpHeaders headers);
}
