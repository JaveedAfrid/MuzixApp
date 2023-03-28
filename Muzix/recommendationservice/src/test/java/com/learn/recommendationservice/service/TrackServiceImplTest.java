package com.learn.recommendationservice.service;

import com.learn.recommendationservice.model.Tracks;
import com.learn.recommendationservice.repository.TrackRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {
    private Tracks trackOne;

    @Mock
    private TrackRepo repository;

    @InjectMocks
    private TrackServiceImpl service;

    @BeforeEach
    public void setup(){
        trackOne = new Tracks(1,"track1",1);
    }

    @Test
    public void addingTrackWhenNotPresentShouldSaveAndReturnTrack(){

        when(repository.findByTrackId("track1")).thenReturn(Optional.empty());
        when(repository.save(any(Tracks.class))).thenReturn(trackOne);

        String track = service.addTracks(trackOne);
        assertAll(
                ()->{assertNotNull(track);},
                ()->{assertTrue(track.equals("track1"));}
        );

        verify(repository,atLeastOnce()).findByTrackId(anyString());
        verify(repository,times(1)).save(any(Tracks.class));
    }


    @Test
    public void gettingTracksShouldReturnListOfTracks(){
        when(repository.findAll()).thenReturn(List.of(trackOne));
        List<String> topTenTracks = service.getTopTenTracks();
        assertAll(
                ()->{assertNotNull(topTenTracks);},
                ()->{assertTrue(service.getTopTenTracks().containsAll(topTenTracks));}
        );
        verify(repository,times(2)).findAll();
    }

}