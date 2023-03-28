package com.learn.recommendationservice.repository;

import com.learn.recommendationservice.model.Tracks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class TrackRepoTest {

    @Autowired
    TrackRepo repo;

    private Tracks trackFour;
    private List<String> trackList;

    @BeforeEach
    void setUp() {
        trackFour = new Tracks(4,"track4",2);
    }

    @Test
    public void givenTrackToSaveThenShouldReturnSavedTrack() {
        repo.save(trackFour);
        Tracks fetchedTrack = repo.findById(trackFour.getId()).get();
        assertEquals(4, fetchedTrack.getId());
    }

    @Test
    public void givenTrackIdShouldGetCount(){
        repo.save(trackFour);
        int countByTrackId = repo.getCountByTrackId(trackFour.getTrackId());
        assertEquals(2,countByTrackId);
    }

    @Test
    public void givenGetAllTracksThenShouldReturnListOfTracks() {
        Tracks trackOne = new Tracks(1,"track1",1);
        Tracks trackTwo = new Tracks(2,"track2",3);
        Tracks trackThree = new Tracks(3,"track3",2);
        repo.save(trackOne);
        repo.save(trackTwo);
        repo.save(trackThree);

        List<Tracks> blogList = (List<Tracks>) repo.findAll();
        assertTrue(!blogList.isEmpty());
    }



}