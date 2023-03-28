package com.learn.favouritesservices.controller;

import com.learn.favouritesservices.exception.NotFoundException;
import com.learn.favouritesservices.exception.TrackidWithEmailAlreadyExists;
import com.learn.favouritesservices.model.Favourites;
import com.learn.favouritesservices.services.FavouritesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/favourites/")
public class FavouritesController {

    private FavouritesServices services;

    @Autowired
    public FavouritesController(FavouritesServices services) {
        this.services = services;
    }

    @PostMapping("add_favourites")
    public ResponseEntity<Favourites> addFavourites(@RequestBody Favourites favourites) throws TrackidWithEmailAlreadyExists {
        return new ResponseEntity<>(services.addFavourites(favourites), HttpStatus.CREATED);
    }

    @GetMapping("favourites/{email}")
    public ResponseEntity<?> viewTracks(@PathVariable String email){
        return new ResponseEntity<>(services.getFavouritesTracks(email),HttpStatus.OK);
    }

    @DeleteMapping("favourites")
    public ResponseEntity<?> deleteFavourites(@RequestBody Favourites favourites) throws NotFoundException {
        services.deleteFavourites(favourites);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
