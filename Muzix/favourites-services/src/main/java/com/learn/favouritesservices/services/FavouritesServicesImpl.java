package com.learn.favouritesservices.services;

import com.learn.favouritesservices.exception.NotFoundException;
import com.learn.favouritesservices.exception.TrackidWithEmailAlreadyExists;
import com.learn.favouritesservices.model.Favourites;
import com.learn.favouritesservices.repository.FavouritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavouritesServicesImpl implements FavouritesServices {

    private FavouritesRepository repository;

    @Autowired
    public FavouritesServicesImpl(FavouritesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Favourites addFavourites(Favourites favourites) {
        Optional<Favourites> byEmailAndTrackid = repository.findByEmailAndTrackId(favourites.getEmail(), favourites.getTrackId());
        if(byEmailAndTrackid.isEmpty()){
           return repository.save(favourites);
        }else {
            throw new TrackidWithEmailAlreadyExists("Trackid already exists");
        }
    }

    @Override
    public List<String> getFavouritesTracks(String email) {
        List<Favourites> byEmail = repository.findByEmail(email);
        if (byEmail.isEmpty()){
            throw new TrackidWithEmailAlreadyExists("Not Found");
        }else{
            return byEmail.stream().map(fun->fun.getTrackId()).collect(Collectors.toList());
        }
    }

    @Override
    public void deleteFavourites(Favourites favourites) {
        Optional<Favourites> byEmailAndTrackId = repository.findByEmailAndTrackId(favourites.getEmail(), favourites.getTrackId());
        if (byEmailAndTrackId.isEmpty()){
            throw new NotFoundException("Not Found");
        }else {
            repository.deleteByEmailAndTrackId(favourites.getEmail(),favourites.getTrackId());
        }
    }
}