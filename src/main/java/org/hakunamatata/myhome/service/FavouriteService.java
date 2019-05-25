package org.hakunamatata.myhome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hakunamatata.myhome.database.Database;
import org.hakunamatata.myhome.model.Favourite;

public class FavouriteService {
    private Map<Long, Favourite> favourites = Database.getFavourites();

    public List<Favourite> getAllFavourites() {
	return new ArrayList<Favourite>(favourites.values());
    }
    
    public Favourite addFavourite(Favourite favourite) {
	favourites.put(favourite.getDataId(), favourite);
	return favourite;
    }
    
    public Favourite removeFavourite(Favourite favourite) {
	return favourites.remove(favourite.getDataId());
    }
}
