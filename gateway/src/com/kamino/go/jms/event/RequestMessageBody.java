package com.kamino.go.jms.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.server.ContainerRequest;

import com.google.common.collect.Maps;
import com.kamino.go.jms.message.MessageBody;
import com.kamino.go.rest.filter.RequestName;

public class RequestMessageBody implements MessageBody {
	private RequestName requestName;
	private ContainerRequestContext requestContext;
	
	public RequestMessageBody(
			RequestName requestName,
			ContainerRequestContext requestContext) {
		this.requestName = requestName;
		this.requestContext = requestContext;
	}

	private RequestName getRequestName() {
		return requestName;
	}

	private ContainerRequestContext getRequestContext() {
		return requestContext;
	}

	@Override
	public String getName() {
		return getRequestName().value();
	}

	@Override
	public Map<String, Object> getBody() {
		Map<String, Object> body = new HashMap<>();
		body.put("body", getRequestBody());
		body.put("pathParameters", getPathParameters());
		return body;
	}
	
	private Map<String, Object> getRequestBody() {
		if(getRequestContext() instanceof ContainerRequest) {
			ContainerRequest request = (ContainerRequest) getRequestContext();
			request.bufferEntity();
			return convertToMap(request.readEntity(Form.class)
									   .asMap());
		}
		return Maps.newHashMap();
	}
	
	private Map<String, Object> getPathParameters() {
		return convertToMap(getRequestContext().getUriInfo()
							  				   .getPathParameters());
	}
	
	private Map<String, Object> convertToMap(MultivaluedMap<String, String> multivaluedMap) {
		return multivaluedMap.entrySet().stream()
							 .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}

}
