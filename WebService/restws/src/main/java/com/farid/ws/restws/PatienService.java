package com.farid.ws.restws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.farid.ws.restws.model.Patient;

@Consumes("application/xml,application/json") // tipos de datos que soporta nuestro servicio
@Produces("application/xml,application/json") // tipos de datos que devuelve nuestro servicio

@Path("/patientservice") // relative url
public interface PatienService {

	// obtener todos los records
	@Path("/patients") // client can use to retrieve values
	@GET // esta anotacion hace el binding con http
	List<Patient> getPatients();

	// obtener recor especifico
	@Path("/patients/{id}") // path variables, nos permiten pasar parametros desde la url
	@GET // esta anotacion hace el binding con http
	Patient getPatient(@PathParam(value = "id") Long id);// @PathParam nos permite setear id como parametro

	// crear nuevo record
	@Path("/patients") // client can use to retrieve values
	@POST
	Response createPatient(Patient patient);

	// actualizar record existente
	@Path("/patients") // client can use to retrieve values
	@PUT
	Response updatePatient(Patient patient);

	// eliminar record existente
	@Path("/patients/{id}") // client can use to retrieve values
	@DELETE
	Response updatePatient(@PathParam(value = "id") Long id);
}
