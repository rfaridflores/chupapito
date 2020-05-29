package com.farid.ws.restws;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import com.farid.ws.restws.model.ChecksList;

@Path("/checkprocessingservice")
public interface CheckProcessor {
	
	@POST
	@Path("/checks")
	//@Suspended AsyncResponse response tells apache that method it's asynchronus
	public void processChecks(@Suspended AsyncResponse response,ChecksList checksList);

}
