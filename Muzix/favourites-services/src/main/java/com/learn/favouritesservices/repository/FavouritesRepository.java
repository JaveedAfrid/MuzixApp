package com.learn.favouritesservices.repository;

import com.learn.favouritesservices.model.Favourites;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouritesRepository extends MongoRepository<Favourites,String> {
    Optional<Favourites> findByEmailAndTrackId(String email, String trackId);
    List<Favourites> findByEmail(String email);
    void deleteByEmailAndTrackId(String email,String trackid);
}