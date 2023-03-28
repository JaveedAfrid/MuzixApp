package com.learn.recommendationservice.controller;

import com.learn.recommendationservice.model.Tracks;
import com.learn.recommendationservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/recommend/")
public class TrackController {

    private TrackService service;

    @Autowired
    public TrackController(TrackService service) {
        this.service = service;
    }

    @PostMapping("tracks")
    public ResponseEntity<?> addTracks(@RequestBody Tracks track){
        return new ResponseEntity<>(service.addTracks(track), HttpStatus.CREATED);
    }

    @GetMapping("tracksTen")
    public ResponseEntity<?> getTopTenTracks(){
        return new ResponseEntity<>(service.getTopTenTracks(),HttpStatus.OK);
    }

}
