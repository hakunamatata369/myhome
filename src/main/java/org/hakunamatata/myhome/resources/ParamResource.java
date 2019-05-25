package org.hakunamatata.myhome.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("param")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class ParamResource {

    @GET
    @Path("annotations")
    public String getIt(@MatrixParam("key") String key, @HeaderParam("authticket") String authTicket,
	    @CookieParam("JSESSIONID") String cookieName) {

	// @FormParam("formkey") String fkey

	if (!authTicket.equals("hakunamatata"))
	    return "Not a valid authentication ticket";

	return (key.length() > 0) ? "Got matrix param value : " + key + ", Cookie param value : " + cookieName
		: "No key value";
    }

    @GET
    @Path("context")
    public String get(@Context UriInfo uriinfo, @Context HttpHeaders httpheaders) {
	String baseurl = uriinfo.getBaseUri().toString();
	String cookies = httpheaders.getCookies().toString();
	return "baseurl = " + baseurl + ", cookies = " + cookies;
    }
}
