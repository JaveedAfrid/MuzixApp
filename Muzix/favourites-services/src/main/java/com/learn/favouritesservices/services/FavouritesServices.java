package com.learn.favouritesservices.services;

import com.learn.favouritesservices.model.Favourites;

import java.util.List;
import java.util.Optional;

public interface FavouritesServices {
    Favourites addFavourites(Favourites favourites);
    List<String> getFavouritesTracks(String email);
    void deleteFavourites(Favourites favourites);
}