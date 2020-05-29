package com.farid.ws.restwsclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.farid.ws.restwsclient.model.Patient;

//Standalone client
public class PatientWSClient {

	private static final String PATIENTS = "/patients";
	private static final String PATIENT_SERVICE_URL = "http://localhost:8080/restws/services/patientservice";

	public static void main(String[] args) {
		// creating client instance
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(PATIENT_SERVICE_URL).path(PATIENTS).path("/{id}").resolveTemplate("id", 123);
		Builder request = target.request();
		
		
		// showing result similar to GET method
		Patient patient = request.get(Patient.class);// telling jaxrs qhat type of data we are getting back
		System.out.println(patient.getId());
		System.out.println(patient.getName());

		// updating result similar to PUT method
		patient.setName("Ojala");
		WebTarget putTarget = client.target(PATIENT_SERVICE_URL).path(PATIENTS);
		Response updateResponse = putTarget.request().put(Entity.entity(patient, MediaType.APPLICATION_XML));
		System.out.println(updateResponse.getStatus());
		updateResponse.close();// Clean up the resources, we must do it when we use response

		// create a patient similar to POST method
		Patient newPatient = new Patient();
		newPatient.setName("Gabriel");
		WebTarget posTarget = client.target(PATIENT_SERVICE_URL).path(PATIENTS);
		Patient createdPatient = posTarget.request().post(Entity.entity(patient, MediaType.APPLICATION_XML),
				Patient.class);

		System.out.println("Created Patien Id: " + createdPatient.getId());

		// Deleteing client DELETE method
		WebTarget deleteTarget = client.target(PATIENT_SERVICE_URL).path(PATIENTS).path("/{id}").resolveTemplate("id", 123);
		deleteTarget.request().delete();
		
		client.close();// clean up resources

	}

}
