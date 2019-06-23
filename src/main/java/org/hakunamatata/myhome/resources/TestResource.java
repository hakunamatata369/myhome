package org.hakunamatata.myhome.resources;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.hakunamatata.myhome.model.MyDate;

@Path("test")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class TestResource {

	@PathParam("pathParam")
	private String pathParamExample;
	@QueryParam("queryParam")
	private String queryParamExample;
	@MatrixParam("matrixParam")
	private String matrixParamExample;
	@HeaderParam("authtoken")
	private String authToken;
	@CookieParam("MyCookie")
	private String cookieValue;

	@GET
	@Path("/date/{dateString}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDateString(@PathParam("dateString") MyDate myDate) {
		return "Got " + myDate.toString();
	}

	@GET
	@Path("/date")
	@Produces(value = { MediaType.TEXT_PLAIN })
	public Date getDate() {
		return Calendar.getInstance().getTime();
	}

	@GET
	@Path("/shortdate")
	@Produces(value = { MediaType.TEXT_PLAIN, "text/shortdate" })
	public Date getShortDate() {
		return Calendar.getInstance().getTime();
	}

	@GET
	@Path("/annotations/{pathParam}")
	public String getPathParam() {
		StringBuilder retVal = new StringBuilder();

		if (!authToken.equals("hakunamatata")) {
			return "Invalid Header Param : Not a valid authentication ticket";
		}

		retVal.append("Header param used = " + authToken);
		retVal.append("\nPath param used = " + pathParamExample);
		retVal.append("\nQuery param used = " + queryParamExample);
		retVal.append("\nCookie param used = " + cookieValue);

		return retVal.toString();
	}

	@GET
	@Path("/annotations")
	public String getMatrixParam() {
		return "Matrix param used = " + matrixParamExample;
	}

	@GET
	@Path("context")
	public String get(@Context UriInfo uriinfo, @Context HttpHeaders httpheaders) {
		String baseurl = uriinfo.getBaseUri().toString();
		String cookies = httpheaders.getCookies().toString();
		return "baseurl = " + baseurl + ", cookies = " + cookies;
	}
}
