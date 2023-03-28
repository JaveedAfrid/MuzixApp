package com.learn.recommendationservice.service;

import com.learn.recommendationservice.model.Tracks;

import java.util.List;

public interface TrackService {
    String addTracks(Tracks track);
    List<String> getTopTenTracks();
}
