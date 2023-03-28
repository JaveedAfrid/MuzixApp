package com.learn.favouritesservices.service;

import com.learn.favouritesservices.exception.NotFoundException;
import com.learn.favouritesservices.exception.TrackidWithEmailAlreadyExists;
import com.learn.favouritesservices.model.Favourites;
import com.learn.favouritesservices.repository.FavouritesRepository;
import com.learn.favouritesservices.services.FavouritesServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FavouritesServiceImplTest {

    private Favourites favourites;
    @Mock
    private FavouritesRepository repository;
    @InjectMocks
    private FavouritesServicesImpl services;
    @BeforeEach
    public void setup(){
        favourites= new Favourites("1","javeed@gmail.com","tra.571665655");
    }


    @Test
    public void givenFavouritesWithTrackIdAndEmailIdDoesNotExistsThenAddFavourites(){
        when(repository.findByEmailAndTrackId(favourites.getEmail(), favourites.getTrackId())).thenReturn(Optional.empty());
        when(repository.save(any(Favourites.class))).thenReturn(favourites);

        Favourites favourite = services.addFavourites(this.favourites);
        assertAll(
                ()->{assertNotNull(favourite);},
                ()->{assertTrue(favourite.getEmail().equals("javeed@gmail.com"));},
                ()->{assertTrue(favourite.getTrackId().equals("tra.571665655"));}
        );
        verify(repository,atLeastOnce()).findByEmailAndTrackId(anyString(),anyString());
        verify(repository,times(1)).save(any(Favourites.class));
    }

    @Test
    public void givenFavouritesWithTrackIdAndEmailAlreadyExistsThenThrowAlreadyExistException() throws TrackidWithEmailAlreadyExists{
        when(repository.findByEmailAndTrackId("javeed@gmail.com","tra.571665655")).thenReturn(Optional.of(favourites));
        assertThrows(TrackidWithEmailAlreadyExists.class,()->services.addFavourites(favourites));
        verify(repository).findByEmailAndTrackId("javeed@gmail.com","tra.571665655");
    }

    @Test
    public void givenEmailAlreadyExistsThenGetFavouriteTracks(){
        when(repository.findByEmail(favourites.getEmail())).thenReturn(List.of(favourites));
        List<String> favouritesTracks = services.getFavouritesTracks(favourites.getEmail());
        assertAll(
                ()->{assertNotNull(favouritesTracks);},
                ()->{assertTrue(services.getFavouritesTracks(favourites.getEmail()).containsAll(favouritesTracks));}
        );
        verify(repository,times(2)).findByEmail(anyString());
    }

    @Test
    public void givenFavouritesWithTrackIdAndEmailDoesNotExistsThenShouldThrowNotFoundException(){
        when(repository.findByEmail(favourites.getEmail())).thenReturn(Collections.emptyList());
        assertThrows(TrackidWithEmailAlreadyExists.class,()->services.getFavouritesTracks(favourites.getEmail()));
        verify(repository,atLeastOnce()).findByEmail(anyString());
    }

    @Test
    public void givenFavouritesWithTrackIdAndEmailExistsThenShouldDeleteTrackAndReturnVoid(){
        when(repository.findByEmailAndTrackId(favourites.getEmail(), favourites.getTrackId())).thenReturn(Optional.of(favourites));
        doNothing().when(repository).deleteByEmailAndTrackId(favourites.getEmail(), favourites.getTrackId());
        services.deleteFavourites(favourites);
        verify(repository,atLeastOnce()).findByEmailAndTrackId(anyString(),anyString());
        verify(repository,atLeastOnce()).deleteByEmailAndTrackId(anyString(),anyString());
    }

    @Test
    public void givenFavouritesWithTrackIdAndEmailDoesNotExistsThenShouldNotFoundException(){
        when(repository.findByEmailAndTrackId(favourites.getEmail(), favourites.getTrackId())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class,()->services.deleteFavourites(favourites));
        verify(repository,atLeastOnce()).findByEmailAndTrackId(anyString(),anyString());
    }

    }
