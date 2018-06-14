package com.kamino.go.rest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/echo")
@Produces(MediaType.TEXT_PLAIN)
public interface EchoResource {
	public static final String REQUEST_ECHO = "echo";

	@GET
	@Path("/{text}")
	public String echo(@PathParam("text") String text);
	
}
