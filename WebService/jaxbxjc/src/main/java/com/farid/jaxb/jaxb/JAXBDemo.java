package com.farid.jaxb.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.bharatthippireddy.patient.Patient;

public class JAXBDemo {

	public static void main(String[] args) {
		
//MARSHALL PROCESS
		// creando un objeto context que usa como parametro la clase que deseamsos hacer marshall
		//es decir, convertir de java class a xml
		
		try {
			JAXBContext context =JAXBContext.newInstance(Patient.class);
			
			//create marshaller
			Marshaller marshaller= context.createMarshaller();
			
			//create Patien object, class to be marshall 
			Patient patient = new Patient();
			patient.setId(123);
			patient.setName("Simon");
			
			//create marshaller
			StringWriter stringWriter=new StringWriter();
			marshaller.marshal(patient, stringWriter);
			System.out.println(stringWriter);
			
			
			
//UNMARSHALL PROCESS
			//create Unmarshall object
			Unmarshaller unmarshaller=context.createUnmarshaller();
			
			//Configure output of it, can be printed, or stores in a file
			Object objectToBeCast=unmarshaller.unmarshal(new StringReader(stringWriter.toString()));
			
			// Casting unmarshalling object to java class object , in this case to Patient object
			Patient patientResult=(Patient) objectToBeCast;
			
			//hadling results, can be printed , stores in a file, etc.
			System.out.println(patientResult.getName());
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		

	}

}
