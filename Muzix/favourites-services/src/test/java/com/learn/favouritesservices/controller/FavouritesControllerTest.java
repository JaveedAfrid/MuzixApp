package com.learn.favouritesservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.favouritesservices.exception.NotFoundException;
import com.learn.favouritesservices.exception.TrackidWithEmailAlreadyExists;
import com.learn.favouritesservices.model.Favourites;
import com.learn.favouritesservices.services.FavouritesServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FavouritesController.class)
class FavouritesControllerTest {

    private Favourites favourites;

    @MockBean
    private FavouritesServicesImpl services;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setup(){

        favourites = new Favourites("1","javeed@gmail.com","tra.571665655");
    }

    @Test
    public void givenFavouritesWhenDoesExistThenShouldReturn() throws Exception {

        Mockito.when(services.addFavourites(any(Favourites.class))).thenReturn(favourites);
        mockMvc.perform(post("/api/v1/favourites/add_favourites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(favourites))
        )
                .andExpect(status().isCreated());
        verify(services).addFavourites(any(Favourites.class));
    }

    @Test
    public void givenFavouritesWhenDoesNotExistThenShouldThrowException() throws Exception {

        Mockito.when(services.addFavourites(any(Favourites.class))).thenThrow(TrackidWithEmailAlreadyExists.class);
        mockMvc.perform(post("/api/v1/favourites/add_favourites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(favourites))
        )
                .andExpect(status().isConflict());
        verify(services).addFavourites(any(Favourites.class));
    }

    @Test
    public void givenEmailWhenDoesExistThenShouldReturnTracks() throws Exception {

        Mockito.when(services.getFavouritesTracks(anyString())).thenReturn(List.of(anyString()));
                mockMvc.perform(get("/api/v1/favourites/favourites/javeed@gmail.com"))
                .andExpect(status().isOk());
        verify(services).getFavouritesTracks(anyString());
    }

    @Test
    public void givenEmailWhenDoesExistThenShouldThrowException() throws Exception{

        Mockito.when(services.getFavouritesTracks(anyString())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/api/v1/favourites/favourites/javeed@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(favourites))
        )
                .andExpect(status().isNotFound());
        verify(services).getFavouritesTracks(anyString());
    }

    @Test
    public void givenFavouritesWhenDoesExistShouldDeleteTrack() throws Exception{

//        Mockito.doNothing().when(services.deleteFavourites(any(Favourites.class)));
        mockMvc.perform(delete("/api/v1/favourites/favourites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(favourites))
        ).andExpect(status().isAccepted());
        verify(services).deleteFavourites(any(Favourites.class));

    }

}