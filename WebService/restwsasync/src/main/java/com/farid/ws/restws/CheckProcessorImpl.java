package com.farid.ws.restws;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.container.AsyncResponse;

import org.springframework.stereotype.Service;

import com.farid.ws.restws.model.ChecksList;

@Service  
public class CheckProcessorImpl implements CheckProcessor {

	@Override // AsyncResponse response, debe de ir para que sea un metodo asincrono
	public void processChecks(AsyncResponse response, ChecksList checksList) {
		// logic goes here
		new Thread() {// aca estamos creado un thread para utilizar Asyn al maximo
			public void run() {
				if(checksList==null || checksList.getChecks()==null || checksList.getChecks().size()==0) {
					response.resume(new BadRequestException());
				}
				response.resume(true);
			}
		}.start();
	}

}
