package org.hakunamatata.myhome.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.hakunamatata.myhome.model.ErrorMessage;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
	ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 500, "");

	return Response.status(Status.INTERNAL_SERVER_ERROR)
		.entity(errorMessage)
		.build();
    }
}
