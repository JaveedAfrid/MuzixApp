package com.learn.favouritesservices.repository;

import com.learn.favouritesservices.FavouritesServicesApplication;
import com.learn.favouritesservices.model.Favourites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FavouritesServicesApplication.class)
@ExtendWith(SpringExtension.class)
class FavouritesRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private FavouritesRepository repository;
    private Favourites favourites;

    @Autowired
    public FavouritesRepositoryTest(FavouritesRepository repository) {
        this.repository = repository;
    }

    @BeforeEach
    void setup() {
        favourites = new Favourites("1", "javeed@gmail.com", "tra.571665655");
        repository.save(favourites);
    }

    @Test
    public void givenEmailAndTrackIdShouldReturnFavourites() {
        Optional<Favourites> byEmailAndTrackId = repository.findByEmailAndTrackId("javeed@gmail.com", "tra.571665655");
        Favourites favourite = byEmailAndTrackId.get();
        assertTrue(byEmailAndTrackId.isPresent());
        assertEquals("javeed@gmail.com", favourites.getEmail());

    }

    @Test
    public void givenUsNotPresentThenReturnOptionalWithUser() {

        Optional<Favourites> favourites1 = repository.findByEmailAndTrackId("javee@gmail.com", "tra.571665655");
        assertTrue(favourites1.isEmpty());

    }

    @Test
    public void givenUserNamePresentThenReturnUser() {

        List<Favourites> fav = repository.findByEmail("javeed@gmail.com");
        assertTrue(!fav.isEmpty());
        Favourites favourites = fav.get(0);
        assertEquals("javeed@gmail.com", favourites.getEmail());

    }

    @Test
    public void givenUserNameNotPresentThenReturnUserNotFound() {

        List<Favourites> fav = repository.findByEmail("javee@gmail.com");
        assertTrue(fav.isEmpty(),"User Not Found");
    }

    @Test
    public void givenUserPresentByIdThenDelete() {
        repository.deleteByEmailAndTrackId("javeed@gmail.com", "tra.571665655");
        Optional<Favourites> byEmailAndTrackId = repository.findByEmailAndTrackId("javeed@gmail.com", "tra.571665655");
        assertFalse(byEmailAndTrackId.isPresent());

    }

    @Test
    public void givenUserNotPresentByIdThenReturnUserNotFound(){
        repository.deleteByEmailAndTrackId("javee@gmail.com", "tra.571665658");
        Optional<Favourites> byEmailAndTrackId = repository.findByEmailAndTrackId("javee@gmail.com", "tra.571665658");
        assertTrue(byEmailAndTrackId.isEmpty(),"User Not Found");
    }

}