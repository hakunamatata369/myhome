package org.hakunamatata.myhome.resources;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hakunamatata.myhome.model.Node;
import org.hakunamatata.myhome.model.ResponseMessage;
import org.hakunamatata.myhome.service.FavouriteService;

@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class FavouritesResource {

	ResponseMessage responseMessage;
	FavouriteService favouriteService = new FavouriteService();

	@GET
	public Collection<Node> getAllFavourites(@PathParam("memberId") Long memberId) {
		return favouriteService.getAllFavourites(memberId);
	}

	@PUT
	@Path("/{dataId}")
	public Response addOrRemoveFavourite(@PathParam("memberId") Long memberId, @PathParam("dataId") Long dataId,
			@QueryParam("action") String action) {
		if (action.equals("add")) {
			favouriteService.addFavourite(memberId, dataId);
			responseMessage = new ResponseMessage("Successfully added as a favourite", 200);
		} else if (action.equals("remove")) {
			favouriteService.removeFavourite(memberId, dataId);
			responseMessage = new ResponseMessage("Successfully removed as a favourite", 200);
		} else {
			responseMessage = new ResponseMessage("Unknown action specified", 500);
		}

		return Response.ok(responseMessage).build();
	}
}
