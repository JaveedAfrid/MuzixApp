package com.learn.recommendationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.recommendationservice.model.Tracks;
import com.learn.recommendationservice.service.TrackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TrackController.class)
class TrackControllerTest {

    private Tracks trackOne;
    private List<String> trackList;

    @MockBean
    private TrackServiceImpl service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        trackOne = new Tracks(1,"track1",1);
        trackList = Arrays.asList("track1","track2","track3");
    }

    @Test
    public void addingNewTrackShouldReturnCreatedStatus() throws Exception {
        when(service.addTracks(any(Tracks.class))).thenReturn(anyString());
        mockMvc.perform(post("/api/v1/recommend/tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(trackOne))
        ).andExpect(status().isCreated());
        verify(service).addTracks(any(Tracks.class));
    }

    @Test
    public void gettingTopTenTracksShouldReturnOkStatus() throws Exception {
        when(service.getTopTenTracks()).thenReturn(trackList);
        mockMvc.perform(get("/api/v1/recommend/tracksTen")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(trackList))
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

        verify(service).getTopTenTracks();
    }
}