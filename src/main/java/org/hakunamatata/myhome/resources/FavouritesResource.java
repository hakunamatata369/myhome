package org.hakunamatata.myhome.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.hakunamatata.myhome.model.Favourite;
import org.hakunamatata.myhome.service.FavouriteService;

@Path("/")
public class FavouritesResource {

    FavouriteService favouriteService = new FavouriteService();

    @GET
    public List<Favourite> getAllFavourites() {
	return favouriteService.getAllFavourites();
    }

    @POST
    @Path("/{dataId}")
    public Favourite addFavourite(@PathParam("memberId") Long memberId, @PathParam("dataId") Long dataId) {
	return favouriteService.addFavourite( new Favourite( memberId, dataId ));
    }

    @DELETE
    @Path("/{dataId}")
    public void removeFavourite(@PathParam("memberId") Long memberId, @PathParam("dataId") Long dataId) {
	favouriteService.removeFavourite( new Favourite( memberId, dataId ) );
    }

}
