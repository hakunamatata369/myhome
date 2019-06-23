package org.hakunamatata.myhome.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hakunamatata.myhome.model.House;
import org.hakunamatata.myhome.model.ResponseMessage;
import org.hakunamatata.myhome.service.HouseService;

@Path("/houses")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class HouseResource {

	@Context
	UriInfo uriInfo;
	ResponseMessage responseMessage;
	@PathParam("houseId")
	private long houseId;
	private HouseService houseService = new HouseService();

	@GET
	public List<House> getHouses() {
		return houseService.getAllHouses();
	}

	@GET
	@Path("/{houseId}")
	public Response getHouse() {
		House house = houseService.getHouse(houseId);
		return Response.ok(house).build();
	}

	@POST
	public Response addHouse(House house) {
		house = houseService.addHouse(house);
		return Response.created(uriInfo.getAbsolutePath()).entity(house).build();
	}

	@PUT
	@Path("/{houseId}")
	public Response updateHouse(House house) {
		house.setDataId(houseId);
		houseService.updateHouse(house);
		return Response.ok(house).build();
	}

	@DELETE
	@Path("/{houseId}")
	public Response deleteHouse() {
		houseService.deleteHouse(houseId);
		responseMessage = new ResponseMessage("Successfully deleted the house with Id:" + houseId, 200);
		return Response.ok(responseMessage).build();
	}

	@DELETE
	public Response deleteHouses() {
		houseService.deleteAllHouses();
		responseMessage = new ResponseMessage("Successfully deleted all the houses", 200);
		return Response.ok(responseMessage).build();
	}
}