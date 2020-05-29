package com.farid.ws.restws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.farid.ws.restws.exceptions.PatientBusinessException;
import com.farid.ws.restws.model.Patient;

@Service // spring annotaion
public class PatientServiceImpl implements PatienService {

	// in memmory map will act like db
	Map<Long, Patient> patients = new HashMap<>();
	long currentId = 123;

	public PatientServiceImpl() {
		init();
	}

	void init() {
		Patient patient = new Patient();
		patient.setId(currentId);
		patient.setName("Simon");
		patients.put(patient.getId(), patient);
	}

	@Override
	public List<Patient> getPatients() {
		Collection<Patient> results = patients.values();// like retrieve all values from data base
		List<Patient> response = new ArrayList<>(results);
		return response;
	}

	@Override
	public Patient getPatient(Long key) {
		if(patients.get(key)==null) {
			//handling standard HTTP exception
			//throw new WebApplicationException(Response.Status.NOT_FOUND);
			throw new NotFoundException();
		}
		return patients.get(key);
	}

	@Override
	public Response createPatient(Patient patient) {
		patient.setId(++currentId);
		patients.put(patient.getId(), patient);// patient come in the request
		return Response.ok(patient).build();
	}

	@Override
	public Response updatePatient(Patient patient) {
		Patient actualPatient = patients.get(patient.getId());
		Response response;
		if (actualPatient != null) {
			patients.put(patient.getId(), patient);
			response = Response.ok().build();
		} else {
			//response = Response.notModified().build();
			throw new PatientBusinessException();//with this we can create exception mappers
		}
		return response;
	}

	@Override
	public Response updatePatient(Long id) {
		Patient actualPatient = patients.get(id);
		Response response;
		if (actualPatient != null) {
			patients.remove(id);
			response = Response.ok().build();
		} else {
			response = Response.notModified().build();
		}
		return response;
	}
}
