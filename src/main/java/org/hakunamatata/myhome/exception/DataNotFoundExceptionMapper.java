package org.hakunamatata.myhome.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hakunamatata.myhome.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException e) {
	ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),404,"");
	
	return Response.status(Status.NOT_FOUND)
		.entity(errorMessage)
		.build();
    }

}
